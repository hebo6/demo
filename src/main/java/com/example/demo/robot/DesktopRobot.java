package com.example.demo.robot;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

@Slf4j
public class DesktopRobot {
    static final long loopInterval = 1000;
    static final long pressInterval = 200;
    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            log.error("初始化robot异常", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //下面两个方法 二选一 执行
//        clickMouse(100);
        pressKeys(100);
    }

    @SuppressWarnings("SameParameterValue")
    private static void clickMouse(int times) throws InterruptedException {
        log.info("5秒后开始...");
        Thread.sleep(5000);
        for (int i = 0; i < times; i++) {
            clickMouseOnce();
            Thread.sleep(loopInterval);
        }
    }

    private static void clickMouseOnce() throws InterruptedException {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(pressInterval);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @SuppressWarnings("SameParameterValue")
    private static void pressKeys(int times) throws InterruptedException {
        log.info("5秒后开始...");
        Thread.sleep(5000);
        for (int i = 0; i < times; i++) {
            pressKeysOnce();
            Thread.sleep(loopInterval);
        }
    }

    private static void pressKeysOnce() throws InterruptedException {
        robot.keyPress(KeyEvent.VK_SHIFT);
        Thread.sleep(pressInterval);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(pressInterval);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(pressInterval);
        robot.keyRelease(KeyEvent.VK_SHIFT);

        Thread.sleep(pressInterval);
        robot.keyPress(KeyEvent.VK_SPACE);
        Thread.sleep(pressInterval);
        robot.keyRelease(KeyEvent.VK_SPACE);

        Thread.sleep(pressInterval);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(pressInterval);
        robot.keyRelease(KeyEvent.VK_TAB);

        Thread.sleep(pressInterval);
        robot.keyPress(KeyEvent.VK_SPACE);
        Thread.sleep(pressInterval);
        robot.keyRelease(KeyEvent.VK_SPACE);
    }
}
