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
package org.uitnet.testing.smartfwk.ui.standard.domobj;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Region;
import org.testng.Assert;
import org.uitnet.testing.smartfwk.ui.core.appdriver.SmartAppDriver;
import org.uitnet.testing.smartfwk.ui.core.objects.DOMObject;
import org.uitnet.testing.smartfwk.ui.core.objects.DOMObjectValidator;
import org.uitnet.testing.smartfwk.ui.core.objects.NewTextLocation;
import org.uitnet.testing.smartfwk.ui.core.objects.link.HyperlinkValidator;
import org.uitnet.testing.smartfwk.ui.core.objects.scrollbar.Scrollbar;
import org.uitnet.testing.smartfwk.ui.core.objects.validator.mechanisms.TextMatchMechanism;
import org.uitnet.testing.smartfwk.ui.core.utils.WebElementUtil;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class HyperlinkValidatorSD extends HyperlinkValidator {
	protected DOMObjectValidator domObjValidator;

	public HyperlinkValidatorSD(SmartAppDriver appDriver, HyperlinkSD uiObject, Region region) {
		super(appDriver, uiObject, region);
		domObjValidator = new DOMObjectValidator(appDriver,
				new DOMObject(uiObject.getType(), uiObject.getDisplayName(), uiObject.getPlatformLocators()), region);
	}

	public DOMObjectValidator getDOMObjectValidator() {
		return domObjValidator;
	}

	@Override
	public HyperlinkValidatorSD typeText(String textToType, NewTextLocation location, int maxIterationsToLocateElements) {
		domObjValidator.typeText(textToType, location, maxIterationsToLocateElements);
		return this;
	}

	@Override
	public boolean isPresent(int maxIterationsToLocateElements) {
		return domObjValidator.isPresent(maxIterationsToLocateElements);
	}

	@Override
	public boolean isVisible(int maxIterationsToLocateElements) {
		return domObjValidator.isVisible(maxIterationsToLocateElements);
	}

	@Override
	public HyperlinkValidatorSD click(int maxIterationsToLocateElements) {
		domObjValidator.click(maxIterationsToLocateElements);
		return this;
	}
	
	@Override
	public HyperlinkValidatorSD forceClick(int maxIterationsToLocateElements) {
		domObjValidator.forceClick(maxIterationsToLocateElements);
		return this;
	}

	@Override
	public HyperlinkValidatorSD doubleClick(int maxIterationsToLocateElements) {
		domObjValidator.doubleClick(maxIterationsToLocateElements);
		return this;
	}

	@Override
	public HyperlinkValidatorSD rightClick(int maxIterationsToLocateElements) {
		domObjValidator.rightClick(maxIterationsToLocateElements);
		return this;
	}

	@Override
	public HyperlinkValidatorSD clickAndHold(int maxIterationsToLocateElements) {
		domObjValidator.clickAndHold(maxIterationsToLocateElements);
		return this;
	}

	@Override
	public HyperlinkValidatorSD release(int maxIterationsToLocateElements) {
		domObjValidator.release(maxIterationsToLocateElements);
		return this;
	}
	
	@Override
	public HyperlinkValidatorSD mouseHoverOver(int maxIterationsToLocateElements) {
		domObjValidator.mouseHoverOver(maxIterationsToLocateElements);
		return this;
	}

	@Override
	public HyperlinkValidatorSD performKeyDown(Keys keys, int maxIterationsToLocateElements) {
		domObjValidator.performKeyDown(keys, maxIterationsToLocateElements);
		return this;
	}

	@Override
	public HyperlinkValidatorSD performKeyUp(Keys keys, int maxIterationsToLocateElements) {
		domObjValidator.performKeyUp(keys, maxIterationsToLocateElements);
		return this;
	}

	@Override
	public HyperlinkValidatorSD performKeyPressed(Keys keys, int maxIterationsToLocateElements) {
		domObjValidator.performKeyPressed(keys, maxIterationsToLocateElements);
		return this;
	}

	@Override
	public HyperlinkValidatorSD scrollElementOnViewport(Scrollbar scrollbar) {
		domObjValidator.scrollElementOnViewport(scrollbar);
		return this;
	}

	@Override
	public HyperlinkValidatorSD validateName(String expectedValue, TextMatchMechanism validationMechanism,
			int maxIterationsToLocateElements) {
		try {
			for (int i = 0; i <= maxIterationsToLocateElements; i++) {
				try {
					String actualValue = WebElementUtil.getElementText(appDriver, domObjValidator.getUIObject(), 0);

					validateTextValue(actualValue, expectedValue, validationMechanism);
					return this;
				} catch (Throwable th) {
					if (i == maxIterationsToLocateElements) {
						throw th;
					}
				}
				appDriver.waitForSeconds(2);
			}
		} catch (Throwable th) {
			Assert.fail("Failed to validate expected value '" + expectedValue + "' for element '"
					+ uiObject.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public String getName(int maxIterationsToLocateElements) {
		WebElement webElem = domObjValidator.findElement(maxIterationsToLocateElements);
		String value = webElem.getText();
		return value;
	}

	@Override
	public WebElement findElement(int maxIterationsToLocateElements) {
		return domObjValidator.findElement(maxIterationsToLocateElements);
	}

	@Override
	public WebElement findElementNoException(int maxIterationsToLocateElements) {
		return domObjValidator.findElementNoException(maxIterationsToLocateElements);
	}

	@Override
	public List<WebElement> findElements(int maxIterationsToLocateElements) {
		return domObjValidator.findElements(maxIterationsToLocateElements);
	}

	@Override
	public Actions getNewSeleniumActions() {
		return domObjValidator.getNewSeleniumActions();
	}
}
