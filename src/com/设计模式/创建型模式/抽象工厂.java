package com.设计模式.创建型模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 抽象工厂 {
    public static void main(String[] args) {
        // 第一步就要选定一个“大厂”
        ComputerFactory cf = new AmdFactory();
        // 从这个大厂造 CPU
        CPU cpu = cf.makeCPU();
        // 从这个大厂造主板
        MainBoard board = cf.makeMainBoard();
        // 从这个大厂造硬盘
        HardDisk hardDisk = cf.makeHardDisk();

        // 将同一个厂子出来的 CPU、主板、硬盘组装在一起
        Computer result = new Computer(cpu, board, hardDisk);
    }
}
interface HardDisk {

}
interface CPU {

}
interface MainBoard {

}
class Computer {

    public Computer(CPU cpu, MainBoard board, HardDisk hardDisk) {

    }
}
interface ComputerFactory {

    MainBoard makeMainBoard();

    HardDisk makeHardDisk();

    CPU makeCPU();
}
class AmdFactory implements ComputerFactory {

    @Override
    public MainBoard makeMainBoard() {
        return null;
    }

    @Override
    public HardDisk makeHardDisk() {
        return null;
    }

    @Override
    public CPU makeCPU() {
        return null;
    }
}
class IntelFactory implements ComputerFactory {

    @Override
    public MainBoard makeMainBoard() {
        return null;
    }

    @Override
    public HardDisk makeHardDisk() {
        return null;
    }

    @Override
    public CPU makeCPU() {
        return null;
    }
}
