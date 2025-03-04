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
package org.uitnet.testing.smartfwk.ui.standard.imgobj;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Button;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.testng.Assert;
import org.uitnet.testing.smartfwk.ui.core.appdriver.SmartAppDriver;
import org.uitnet.testing.smartfwk.ui.core.commons.ImageSection;
import org.uitnet.testing.smartfwk.ui.core.config.PlatformType;
import org.uitnet.testing.smartfwk.ui.core.objects.ImageObject;
import org.uitnet.testing.smartfwk.ui.core.objects.NewTextLocation;
import org.uitnet.testing.smartfwk.ui.core.objects.scrollbar.Scrollbar;
import org.uitnet.testing.smartfwk.ui.core.objects.textbox.TextBoxValidator;
import org.uitnet.testing.smartfwk.ui.core.objects.validator.mechanisms.TextMatchMechanism;
import org.uitnet.testing.smartfwk.ui.core.utils.ClipboardUtil;
import org.uitnet.testing.smartfwk.ui.core.utils.OSDetectorUtil;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class TextBoxValidatorSI extends TextBoxValidator {
	protected TextBoxSI textBoxObj;

	public TextBoxValidatorSI(SmartAppDriver appDriver, TextBoxSI uiObject, Region region) {
		super(appDriver, uiObject, region);
		this.textBoxObj = uiObject;
	}

	@Override
	@Deprecated
	public TextBoxValidatorSI validateDisabled(int maxIterationsToLocateElements) {
		Assert.fail("validateDisabled() API is not supported by TextBoxSI.");
		return this;
	}

	@Override
	@Deprecated
	public TextBoxValidatorSI validateEnabled(int maxIterationsToLocateElements) {
		Assert.fail("validateEnabled() API is not supported by TextBoxSI.");
		return this;
	}

	@Override
	public boolean isPresent(int maxIterationsToLocateElements) {
		Match m = findElementNoException(maxIterationsToLocateElements);
		return (m != null);
	}

	@Override
	public boolean isVisible(int maxIterationsToLocateElements) {
		return isPresent(maxIterationsToLocateElements);
	}

	@Override
	public TextBoxValidatorSI click(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.click();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse click on TextBox '" + textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}
	
	@Override
	public TextBoxValidatorSI forceClick(int maxIterationsToLocateElements) {
		return click(maxIterationsToLocateElements);
	}

	public TextBoxValidatorSI click(ImageSection imageSection, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			getImageSection(match, imageSection).click();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse click on TextBox '" + textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public TextBoxValidatorSI doubleClick(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.doubleClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse double click on TextBox '" + textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	public TextBoxValidatorSI doubleClick(ImageSection imageSection, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			getImageSection(match, imageSection).doubleClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse double click on TextBox '" + textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public TextBoxValidatorSI rightClick(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.rightClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse right click on TextBox '" + textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	public TextBoxValidatorSI rightClick(ImageSection imageSection, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			getImageSection(match, imageSection).rightClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse right click on TextBox '" + textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public TextBoxValidatorSI clickAndHold(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.mouseDown(Button.LEFT);
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse clickAndHold on TextBox '" + textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public TextBoxValidatorSI release(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.mouseDown(Button.LEFT);
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse clickAndHold on TextBox '" + textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}
	
	@Override
	public TextBoxValidatorSI mouseHoverOver(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.mouseMove();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse hoverover on TextBox '" + textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public TextBoxValidatorSI performKeyDown(Keys keys, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.click();
			match.keyDown(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyDown on TextBox '" + textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public TextBoxValidatorSI performKeyUp(Keys keys, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.click();
			match.keyUp(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyUp ('" + seleniumToSikuliKeyConverter(keys) + "') on TextBox '"
					+ textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public TextBoxValidatorSI performKeyPressed(Keys keys, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.click();
			match.type(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyPressed ('" + seleniumToSikuliKeyConverter(keys) + "') on TextBox '"
					+ textBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public TextBoxValidatorSI typeText(String text, NewTextLocation location, int maxIterationsToLocateElements) {
		return typeText(text, location, 0, true, maxIterationsToLocateElements);
	}
	
	@Override
	public TextBoxValidatorSI typeText(String text, NewTextLocation location, int typeSpeedInMspc, boolean clickBeforeType, int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);
		try {
			if(clickBeforeType) {
				match.click();
			}
			
			switch (location) {
			case start:
				match.type(Key.HOME);
				break;
			case end:
				match.type(Key.END);
				break;
			case replace:				
				if(OSDetectorUtil.getHostPlatform() == PlatformType.mac || OSDetectorUtil.getHostPlatform() == PlatformType.ios_mobile) {
					match.type("a", KeyModifier.CMD);
				} else {
					match.type("a", KeyModifier.CTRL);
				}
				break;
			}
			if(typeSpeedInMspc < 1) {
				match.type(text);
			} else {
				match.delayType(typeSpeedInMspc);
				match.type(text);
			}
			
			// validateTextValue(text, TextMatchMechanism.containsExpectedValue, 0);
		} catch (Throwable th) {
			Assert.fail("Fail to type text '" + text + "' in TextBox '" + textBoxObj.getDisplayName() + "'.");
		}
		return this;
	}

	@Override
	public TextBoxValidatorSI scrollElementOnViewport(Scrollbar scrollbar) {
		// TODO
		return this;
	}

	@Override
	public Match findElement(int maxIterationsToLocateElements) {
		Match match = null;
		for (int i = 0; i <= maxIterationsToLocateElements; i++) {
			try {
				Region region = textBoxObj.getLocation().getRegionOfImageObject(appDriver,
						textBoxObj.getLeftSideImage(appDriver.getAppConfig().getTestPlatformType(),
								appDriver.getAppConfig().getAppType(), appDriver.getAppConfig().getAppWebBrowser()),
						textBoxObj.getRightSideImage(appDriver.getAppConfig().getTestPlatformType(),
								appDriver.getAppConfig().getAppType(), appDriver.getAppConfig().getAppWebBrowser()));
				Assert.assertNotNull(region, "Failed to find TextBox '" + textBoxObj.getDisplayName() + "'.");
				match = new Match(region, 1);
				break;
			} catch (Throwable th) {
				if (i == maxIterationsToLocateElements) {
					Assert.fail("Unable to find TextBox '" + textBoxObj.getDisplayName()
							+ "'. Reason timeout(waited for " + (maxIterationsToLocateElements * 2) + " seconds).", th);
					break;
				}
			}
			appDriver.waitForSeconds(2);
		}
		return match;
	}

	@Override
	public Match findElementNoException(int maxIterationsToLocateElements) {
		Match match = null;
		try {
			match = findElement(maxIterationsToLocateElements);
		} catch (Throwable th) {
			// Do nothing
		}
		return match;
	}

	@Override
	public List<Match> findElements(int maxIterationsToLocateElements) {
		Assert.fail("findElements() api for TextBoxSI element is not implemented.");
		return null;
	}

	public TextBoxValidatorSI dragAndDrop(ImageObject target, Region targetRegion, int maxIterationsToLocateElements) {
		try {
			Match sourceElem = findElement(maxIterationsToLocateElements);
			Match targetElem = target.getValidator(appDriver, targetRegion).findElement(maxIterationsToLocateElements);

			Assert.assertNotNull(sourceElem, "Failed to find TextBox '" + textBoxObj.getDisplayName() + "'.");
			Assert.assertNotNull(targetElem, "Failed to find element '" + target.getDisplayName() + "'.");

			sourceElem.drag(targetElem);
			sourceElem.dropAt(targetElem);
		} catch (Throwable th) {
			Assert.fail("Failed to perform dragAndDrop from source '" + textBoxObj.getDisplayName() + "' to target '"
					+ target.getDisplayName() + "'.", th);
		}
		return this;
	}

	protected Location getImageSection(Match imageMatch, ImageSection imageSection) {
		switch (imageSection) {
		case topLeft:
			return imageMatch.getTopLeft();
		case topRight:
			return imageMatch.getTopRight();
		case bottomLeft:
			return imageMatch.getBottomLeft();
		case bottomRight:
			return imageMatch.getBottomRight();
		case center:
			return imageMatch.getCenter();
		}
		return null;
	}

	@Override
	public TextBoxValidatorSI validateValue(String expectedValue, TextMatchMechanism validationMechanism,
			int maxIterationsToLocateElements) {
		if (textBoxObj.isDisabled()) {
			Match match = findElement(maxIterationsToLocateElements);
			validateTextValue(match.text(), expectedValue, validationMechanism);
		} else {
			validateTextValue(getValue(maxIterationsToLocateElements), expectedValue, validationMechanism);
		}
		return this;
	}

	/**
	 * Used to return value using clipboard method.
	 */
	@Override
	public String getValue(int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);
		if (textBoxObj.isDisabled()) {
			return match.text();
		} else {
			match.click();

			ClipboardUtil.clearContents();
			match.type("ac", KeyModifier.CTRL);

			String contents = ClipboardUtil.getContents();
			ClipboardUtil.clearContents();

			match.click();
			return contents;
		}

	}

	@Override
	@Deprecated
	public boolean isDisabled(int maxIterationsToLocateElements) {
		Assert.fail("isDisabled() API is not supported by TextBox component.");
		return false;
	}

	@Override
	@Deprecated
	public boolean isReadonly(int maxIterationsToLocateElements) {
		Assert.fail("isDisabled() API is not supported by TextBox component.");
		return false;
	}

	@Override
	@Deprecated
	public TextBoxValidatorSI validateReadonly(int maxIterationsToLocateElements) {
		Assert.fail("isDisabled() API is not supported by TextBox component.");
		return this;
	}

	@Override
	@Deprecated
	public TextBoxValidatorSI validateNotReadonly(int maxIterationsToLocateElements) {
		Assert.fail("isDisabled() API is not supported by TextBox component.");
		return this;
	}

	@Override
	@Deprecated
	public Actions getNewSeleniumActions() {
		Assert.fail("getNewSeleniumActions() API is not supported by TextBox component.");
		return null;
	}

	@Override
	public boolean isDisabledButNotReadonly(int maxIterationsToLocateElements) {
		Assert.fail("isDisabledButNotReadonly() API is not supported by TextBox component.");
		return false;
	}

	@Override
	public TextBoxValidatorSI validateDisabledButNotReadonly(int maxIterationsToLocateElements) {
		Assert.fail("validateDisabledButNotReadonly() API is not supported by TextBox component.");
		return this;
	}

	@Override
	public TextBoxValidatorSI validateEnabledButNotReadonly(int maxIterationsToLocateElements) {
		Assert.fail("validateEnabledButNotReadonly() API is not supported by TextBox component.");
		return this;
	}
}
