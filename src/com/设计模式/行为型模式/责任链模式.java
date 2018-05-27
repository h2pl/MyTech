package com.设计模式.行为型模式;


/**
 * Created by 周杰伦 on 2018/5/25.
 * 责任链通常需要先建立一个单向链表，然后调用方只需要调用头部节点就可以了，后面会自动流转下去。比如流程审批就是一个很好的例子，只要终端用户提交申请，根据申请的内容信息，自动建立一条责任链，然后就可以开始流转了。

 有这么一个场景，用户参加一个活动可以领取奖品，但是活动需要进行很多的规则校验然后才能放行，比如首先需要校验用户是否是新用户、今日参与人数是否有限额、全场参与人数是否有限额等等。设定的规则都通过后，才能让用户领走奖品。

 如果产品给你这个需求的话，我想大部分人一开始肯定想的就是，用一个 List 来存放所有的规则，然后 foreach 执行一下每个规则就好了。不过，读者也先别急，看看责任链模式和我们说的这个有什么不一样？
 */
public class 责任链模式 {
    public static void main(String[] args) {
        User user = new User();
        RuleHandler ruleHandler1 = new LimitRuleHandler();
        RuleHandler ruleHandler2 = new LocationRuleHandler();
        RuleHandler ruleHandler3 = new NewUserRuleHandler();
        ruleHandler1.setSuccessor(ruleHandler2);
        ruleHandler2.setSuccessor(ruleHandler3);
        ruleHandler1.apply(user);
    }
}

class User {

    public String getLocation;

    public boolean isNewUser() {
        return true;
    }
}
abstract class RuleHandler {

    // 后继节点
    protected RuleHandler successor;

    public abstract void apply(User User);

    public void setSuccessor(RuleHandler successor) {
        this.successor = successor;
    }
    public RuleHandler getSuccessor() {
        return successor;
    }
}
//接下来，我们需要定义具体的每个节点了。
//
//        校验用户是否是新用户：

class NewUserRuleHandler extends RuleHandler {

    public void apply(User User) {
        if (User.isNewUser()) {
            // 如果有后继节点的话，传递下去
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(User);
            }
        } else {
            throw new RuntimeException("该活动仅限新用户参与");
        }
    }

}
//校验用户所在地区是否可以参与：
class ActivityService {
    boolean isSupportedLocation(String location) {
        return true;
    }

    public int queryRemainedTimes(User user) {
        return 1;
    }
}

class LocationRuleHandler extends RuleHandler {
    private ActivityService activityService = new ActivityService();

    public void apply(User user) {
        boolean allowed = activityService.isSupportedLocation(user.getLocation);
        if (allowed) {
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(user);
            }
        } else  {
            throw new RuntimeException("非常抱歉，您所在的地区无法参与本次活动");
        }
    }


}
//校验奖品是否已领完：

class LimitRuleHandler extends RuleHandler {
    private ActivityService activityService = new ActivityService();

    public void apply(User user) {
        int remainedTimes = activityService.queryRemainedTimes(user); // 查询剩余奖品
        if (remainedTimes > 0) {
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(user);
            }
        } else {
            throw new RuntimeException("您来得太晚了，奖品被领完了");
        }
    }
}