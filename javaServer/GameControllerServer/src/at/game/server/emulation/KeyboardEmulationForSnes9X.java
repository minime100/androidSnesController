package at.game.server.emulation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;

public class KeyboardEmulationForSnes9X {
	Logger logger = Logger.getLogger(getClass());
	Robot robot;
	
	public KeyboardEmulationForSnes9X() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			logger.error(e);
		}
	}
	
	public void dPadDownPress() {
		robot.keyPress(KeyEvent.VK_DOWN);
	}
	public void dPadDownRelease() {
		robot.keyRelease(KeyEvent.VK_DOWN);
	}
	
	public void dPadUpPress() {
		robot.keyPress(KeyEvent.VK_UP);
	}
	public void dPadUpRelease() {
		robot.keyRelease(KeyEvent.VK_UP);
	}
	
	public void dPadLeftPress() {
		robot.keyPress(KeyEvent.VK_LEFT);
	}
	public void dPadLeftRelease() {
		robot.keyRelease(KeyEvent.VK_LEFT);
	}
	
	public void dPadRightPress() {
		robot.keyPress(KeyEvent.VK_RIGHT);
	}
	public void dPadRightRelease() {
		robot.keyRelease(KeyEvent.VK_RIGHT);
	}
	
	public void startPress() {
		robot.keyPress(KeyEvent.VK_SPACE);
	}
	public void startRelease() {
		robot.keyRelease(KeyEvent.VK_SPACE);
	}
	
	public void selectPress() {
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	public void selectRelease() {
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void aPress() {
		robot.keyPress(KeyEvent.VK_V);
	}
	public void aRelease() {
		robot.keyRelease(KeyEvent.VK_V);
	}
	
	public void bPress() {
		robot.keyPress(KeyEvent.VK_C);
	}
	public void bRelease() {
		robot.keyRelease(KeyEvent.VK_C);
	}
	
	public void xPress() {
		robot.keyPress(KeyEvent.VK_X);
	}
	public void xRelease() {
		robot.keyRelease(KeyEvent.VK_X);
	}
	
	public void yPress() {
		robot.keyPress(KeyEvent.VK_D);
	}
	public void yRelease() {
		robot.keyRelease(KeyEvent.VK_D);
	}
}
