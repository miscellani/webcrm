package base.com.zl.selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;



public class OpDevice {

	private Actions action;

	public OpDevice(WebDriver weDriver) {

		action = new Actions(weDriver);

	}

	public void sendKey(Keys theKey) {

		action.sendKeys(theKey).perform();
		action.sendKeys(Keys.NULL);

	}

	public void keyDownup(Keys thekey, String s) {

		action.keyDown(thekey);
		action.sendKeys("m");
		action.keyUp(thekey);
		action.sendKeys(null);

	}

	public void keyDownup(Keys thekey1, Keys thekey2, String s) {

		action.keyDown(thekey1);
		action.keyDown(thekey2);
		action.sendKeys(s);
		action.keyUp(thekey2);
		action.keyUp(thekey1).perform();

	}

	public void dragElement(WebElement element, int xOffset, int yOffset) {

		action.dragAndDropBy(element, xOffset, yOffset);

	}

	public void dragElementTo(WebElement source, WebElement target) {
		action.dragAndDrop(source, target);

	}

	public void mouseRight(WebElement element) {

		action.contextClick(element).perform();

	}

	public void mouseClickElement(WebElement element, String time)
			throws InterruptedException {

		action.clickAndHold(element).perform();
		Thread.sleep(Integer.parseInt(time));
		action.release(element);

	}

	public void mouseClick() {
		action.click();

	}

	public void mouseSuspend(WebElement element, String time)
			throws InterruptedException {

		action.moveToElement(element).perform();

		Thread.sleep(Integer.parseInt(time));

	}
	public void focalElement(WebElement element, String time)
			throws InterruptedException, AWTException {

		action.contextClick(element).perform();
	     Robot robot = new Robot();
		 robot.keyPress(KeyEvent.VK_ESCAPE);


		Thread.sleep(Integer.parseInt(time));

	}
	

}
