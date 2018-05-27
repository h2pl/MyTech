package com.设计模式.结构性模式.适配器模式;

import java.io.File;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 默认适配器 {
}
interface FileAlterationListener {
    void onDirectoryCreate(final File directory);
    void onDirectoryChange(final File directory);
    void onDirectoryDelete(final File directory);
    void onFileCreate(final File file);
    void onFileChange(final File file);
    void onFileDelete(final File file);
}
//此接口的一大问题是抽象方法太多了，如果我们要用这个接口，
// 意味着我们要实现每一个抽象方法，如果我们只是想要监控文件夹中的文件创建和文件删除事件，
// 可是我们还是不得不实现所有的方法，很明显，这不是我们想要的。

//所以，我们需要下面的一个适配器，它用于实现上面的接口，
// 但是所有的方法都是空方法，这样，
// 我们就可以转而定义自己的类来继承下面这个类即可。
class FileAlterationListenerAdaptor implements FileAlterationListener {

    public void onDirectoryCreate(final File directory) {
    }

    public void onDirectoryChange(final File directory) {
    }

    public void onDirectoryDelete(final File directory) {
    }

    public void onFileCreate(final File file) {
    }

    public void onFileChange(final File file) {
    }

    public void onFileDelete(final File file) {
    }

    public class FileMonitor extends FileAlterationListenerAdaptor {

        @Override
        public void onFileCreate(final File file) {
            // 文件创建
            //doSomething();
        }

        @Override
        public void onFileDelete(final File file) {
            // 文件删除
            //doSomething();
        }
    }
}
