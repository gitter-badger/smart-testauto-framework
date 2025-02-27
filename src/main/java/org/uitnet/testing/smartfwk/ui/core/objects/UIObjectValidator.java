/*
 * SmartTestAutoFramework
 * Copyright 2021 and beyond
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.uitnet.testing.smartfwk.ui.core.objects;

import java.awt.Rectangle;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Key;
import org.sikuli.script.Region;
import org.testng.Assert;
import org.uitnet.testing.smartfwk.ui.core.appdriver.SmartAppDriver;
import org.uitnet.testing.smartfwk.ui.core.commons.UIObjectType;
import org.uitnet.testing.smartfwk.ui.core.events.InputEvent;
import org.uitnet.testing.smartfwk.ui.core.events.InputEventType;
import org.uitnet.testing.smartfwk.ui.core.events.KeyboardEvent;
import org.uitnet.testing.smartfwk.ui.core.events.MouseEvent;
import org.uitnet.testing.smartfwk.ui.core.objects.scrollbar.Scrollbar;
import org.uitnet.testing.smartfwk.ui.core.objects.validator.mechanisms.TextMatchMechanism;
import org.uitnet.testing.smartfwk.ui.core.utils.DataMatchUtil;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class UIObjectValidator {
	protected SmartAppDriver appDriver;
	protected UIObject uiObject;
	protected Region region;

	public UIObjectValidator(SmartAppDriver appDriver, UIObject uiObject, Region region) {
		this.appDriver = appDriver;
		this.uiObject = uiObject;
		if (appDriver != null) {
			this.region = (region == null)
					? Region.create(new Rectangle(0, 0,
							Double.valueOf(appDriver.getAppConfig().getBrowserWindowSize().getWidth()).intValue(),
							Double.valueOf(appDriver.getAppConfig().getBrowserWindowSize().getHeight()).intValue()))
					: region;

		}
	}

	public UIObject getUIObject() {
		return uiObject;
	}

	public UIObjectType geUIObjectType() {
		return uiObject.getType();
	}

	public Region getRegion() {
		return region;
	}

	public UIObjectValidator validatePresent(int maxIterationsToLocateElements) {
		Assert.assertTrue(isPresent(maxIterationsToLocateElements),
				"Failed to find element '" + uiObject.getDisplayName() + "'");
		return this;
	}

	public UIObjectValidator validateNotPresent(int maxIterationsToLocateElements) {
		Assert.assertTrue(!isPresent(maxIterationsToLocateElements),
				"Element '" + uiObject.getDisplayName() + "' is already present.");
		return this;
	}

	public <EVENTNAME> UIObjectValidator performAction(InputEvent<EVENTNAME> event, int maxIterationsToLocateElements) {
		if (event.getType() == InputEventType.mouse) {
			MouseEvent mouseEvent = (MouseEvent) event;
			switch (mouseEvent.getName()) {
			case mouseClick:
				click(maxIterationsToLocateElements);
				break;
			case mouseRightClick:
				rightClick(maxIterationsToLocateElements);
				break;
			case mouseDoubleClick:
				doubleClick(maxIterationsToLocateElements);
				break;
			case mouseClickAndHold:
				clickAndHold(maxIterationsToLocateElements);
				break;
			case mouseRelease:
				release(maxIterationsToLocateElements);
				break;
			}
		} else if (event.getType() == InputEventType.keyBoard) {
			KeyboardEvent kbEvent = (KeyboardEvent) event;
			switch (kbEvent.getName()) {
			case kbKeyDown:
				if (kbEvent.getInputText() != null) {
					typeText(kbEvent.getInputText(), kbEvent.getTextLocation(), maxIterationsToLocateElements);
				}
				performKeyDown(kbEvent.getKey(), maxIterationsToLocateElements);
				break;
			case kbKeyUp:
				if (kbEvent.getInputText() != null) {
					typeText(kbEvent.getInputText(), kbEvent.getTextLocation(), maxIterationsToLocateElements);
				}
				performKeyUp(kbEvent.getKey(), maxIterationsToLocateElements);
				break;
			case keyPressed:
				if (kbEvent.getInputText() != null) {
					typeText(kbEvent.getInputText(), kbEvent.getTextLocation(), maxIterationsToLocateElements);
				}
				performKeyPressed(kbEvent.getKey(), maxIterationsToLocateElements);
				break;
			}
		}
		return this;
	}
	
	public UIObjectValidator sendCommandKeys(int maxIterationsToLocateElements, CharSequence... keys) {
		Object elem = findElement(maxIterationsToLocateElements);
		if(elem != null && elem instanceof WebElement) {
			WebElement webElem = (WebElement) elem;
			webElem.sendKeys(Keys.chord(keys));
		}
		return this;
	}

	protected boolean matchTextValue(String actualValue, String expectedValue, TextMatchMechanism validationMechanism) {
		return DataMatchUtil.matchTextValue(actualValue, expectedValue, validationMechanism);
	}

	protected UIObjectValidator validateTextValue(String actualValue, String expectedValue, TextMatchMechanism validationMechanism) {
		DataMatchUtil.validateTextValue(actualValue, expectedValue, validationMechanism);
		return this;
	}

	public String seleniumToSikuliKeyConverter(Keys key) {
		switch (key.name()) {
		case "HOME":
			return Key.HOME;
		case "END":
			return Key.END;
		case "TAB":
			return Key.TAB;
		case "BACK_SPACE":
			return Key.BACKSPACE;
		case "RETURN":
			return Key.ENTER;
		case "ENTER":
			return Key.ENTER;
		case "SHIFT":
			return Key.SHIFT;
		case "CONTROL":
			return Key.CTRL;
		case "ALT":
			return Key.ALT;
		case "ESCAPE":
			return Key.ESC;
		case "SPACE":
			return Key.SPACE;
		case "PAGE_UP":
			return Key.PAGE_UP;
		case "PAGE_DOWN":
			return Key.PAGE_DOWN;
		case "LEFT":
		case "ARROW_LEFT":
			return Key.LEFT;
		case "UP":
		case "ARROW_UP":
			return Key.UP;
		case "RIGHT":
		case "ARROW_RIGHT":
			return Key.RIGHT;
		case "DOWN":
		case "ARROW_DOWN":
			return Key.DOWN;
		case "INSERT":
			return Key.INSERT;
		case "DELETE":
			return Key.DELETE;
		case "SEMICOLON":
			return ";";
		case "F1":
			return Key.F1;
		case "F2":
			return Key.F2;
		case "F3":
			return Key.F3;
		case "F4":
			return Key.F4;
		case "F5":
			return Key.F5;
		case "F6":
			return Key.F6;
		case "F7":
			return Key.F7;
		case "F8":
			return Key.F8;
		case "F9":
			return Key.F9;
		case "F10":
			return Key.F10;
		case "F11":
			return Key.F11;
		case "F12":
			return Key.F12;
		case "META":
			return Key.META;
		case "COMMAND":
			return Key.CMD;
		}
		return null;
	}

	public UIObjectValidator validateVisible(int maxIterationsToLocateElements) {
		Assert.assertTrue(isVisible(maxIterationsToLocateElements),
				"Element '" + uiObject.getDisplayName() + "' is not visible.");
		return this;
	}

	public UIObjectValidator validateNotVisible(int maxIterationsToLocateElements) {
		Assert.assertFalse(isVisible(maxIterationsToLocateElements),
				"Element '" + uiObject.getDisplayName() + "' is visible.");
		return this;
	}

	public UIObjectValidator validateHidden(int maxIterationsToLocateElements) {
		Assert.assertFalse(isVisible(maxIterationsToLocateElements),
				"Element '" + uiObject.getDisplayName() + "' is visible.");
		return this;
	}

	public abstract boolean isPresent(int maxIterationsToLocateElements);

	public abstract boolean isVisible(int maxIterationsToLocateElements);

	public abstract UIObjectValidator click(int maxIterationsToLocateElements);
	
	public abstract UIObjectValidator forceClick(int maxIterationsToLocateElements);

	public abstract UIObjectValidator doubleClick(int maxIterationsToLocateElements);

	public abstract UIObjectValidator rightClick(int maxIterationsToLocateElements);

	public abstract UIObjectValidator clickAndHold(int maxIterationsToLocateElements);
	
	public abstract UIObjectValidator mouseHoverOver(int maxIterationsToLocateElements);

	public abstract UIObjectValidator release(int maxIterationsToLocateElements);

	public abstract UIObjectValidator performKeyDown(Keys keys, int maxIterationsToLocateElements);

	public abstract UIObjectValidator performKeyUp(Keys keys, int maxIterationsToLocateElements);

	public abstract UIObjectValidator performKeyPressed(Keys keys, int maxIterationsToLocateElements);

	public abstract UIObjectValidator typeText(String text, NewTextLocation location, int maxIterationsToLocateElements);

	public abstract UIObjectValidator scrollElementOnViewport(Scrollbar scrollbar);

	public abstract Object findElement(int maxIterationsToLocateElements);

	public abstract Object findElementNoException(int maxIterationsToLocateElements);

	public abstract Object findElements(int maxIterationsToLocateElements);

	public abstract Actions getNewSeleniumActions();
}
