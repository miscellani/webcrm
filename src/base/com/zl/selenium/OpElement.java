package base.com.zl.selenium;

import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import base.com.zl.utils.FileUtil;
import base.com.zl.utils.ocr.ImageFilter;

public class OpElement {




	public boolean matchContById(WebElement element, String content)
			throws Exception {

		String regexcontent = "^[\\s\\S]*" + content + "[\\s\\S]*$";
		boolean b = element.getText().matches(regexcontent);
		return b;

	}

	public boolean isDisplayed(WebElement element) {

		return element.isDisplayed();

	}

/*	public void jsSetAttrValue(WebElement element, String attrname,
			String attrvalue) {

		((JavascriptExecutor) OpWebDriver.webDriver).executeScript(
				"arguments[0].setAttribute(arguments[1],arguments[2])",
				element, attrname, attrvalue);

	}*/

/*	public void deleteAttr(WebElement element, String attrname) {

		((JavascriptExecutor) OpWebDriver.webDriver).executeScript(
				"arguments[0].removeAttribute(arguments[1],arguments[2])",
				element, attrname);

	}*/

	public String getCssValue(WebElement element, String CssValue) {
		return element.getCssValue(CssValue);

	}

/*	public static void dClick(WebElement webElement)
			throws InterruptedException {

		Actions act = new Actions(OpWebDriver.webDriver);
		act.doubleClick(webElement).build().perform();

	}*/

	public void selectByValue(WebElement element, String Value)
			throws InterruptedException {

		Select sl = new Select(element);
		sl.selectByValue(Value);

	}

	public void cancelSelectByValue(WebElement element, String attrValue)
			throws InterruptedException {

		Select sl = new Select(element);
		sl.deselectByValue(attrValue);

	}

	public void cancelAllSelect(WebElement element) throws InterruptedException {

		Select sl = new Select(element);
		sl.deselectAll();

	}

	public List getAllOptions(WebElement element) throws InterruptedException {

		Select sl = new Select(element);
		return (List) sl.getOptions();

	}

	public String getSelectedOption(WebElement element)
			throws InterruptedException {

		Select sl = new Select(element);
		return sl.getFirstSelectedOption().getText();

	}

	public List getAllSelectedOptions(WebElement element) {

		Select sl = new Select(element);
		List stringsl = null;
		for (WebElement ops : sl.getAllSelectedOptions())
			stringsl.add(ops.getText());

		return stringsl;

	}

	public boolean isOptionMultiple(WebElement element) {

		Select sl = new Select(element);
		if (sl.isMultiple()) {
			return true;
		} else {
			return false;
		}
	}

	public Point getPoint(WebElement element) {

		return element.getLocation();

	}

	public Dimension getSize(WebElement element) {

		return element.getSize();

	}

}
