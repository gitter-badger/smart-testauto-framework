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
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.testng.Assert;
import org.uitnet.testing.smartfwk.ui.core.appdriver.SmartAppDriver;
import org.uitnet.testing.smartfwk.ui.core.commons.ImageSection;
import org.uitnet.testing.smartfwk.ui.core.commons.ItemList;
import org.uitnet.testing.smartfwk.ui.core.objects.ImageObject;
import org.uitnet.testing.smartfwk.ui.core.objects.NewTextLocation;
import org.uitnet.testing.smartfwk.ui.core.objects.listbox.ListBoxValidator;
import org.uitnet.testing.smartfwk.ui.core.objects.scrollbar.Scrollbar;
import org.uitnet.testing.smartfwk.ui.core.objects.validator.mechanisms.TextMatchMechanism;
import org.uitnet.testing.smartfwk.ui.core.utils.ClipboardUtil;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ListBoxValidatorSI extends ListBoxValidator {
	protected ListBoxSI listBoxObj;

	public ListBoxValidatorSI(SmartAppDriver appDriver, ListBoxSI uiObject, Region region) {
		super(appDriver, uiObject, region);
		this.listBoxObj = uiObject;
	}

	@Override
	@Deprecated
	public ListBoxValidatorSI validateDisabled(int maxIterationsToLocateElements) {
		Assert.fail("validateDisabled() API is not supported by ListBoxSI.");
		return this;
	}

	@Override
	@Deprecated
	public ListBoxValidatorSI validateEnabled(int maxIterationsToLocateElements) {
		Assert.fail("validateEnabled() API is not supported by ListBoxSI.");
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
	public ListBoxValidatorSI click(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.click();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse click on Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}
	
	@Override
	public ListBoxValidatorSI forceClick(int maxIterationsToLocateElements) {
		return click(maxIterationsToLocateElements);
	}

	public ListBoxValidatorSI click(ImageSection imageSection, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			getImageSection(match, imageSection).click();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse click on Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public ListBoxValidatorSI doubleClick(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.doubleClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse double click on Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	public ListBoxValidatorSI doubleClick(ImageSection imageSection, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			getImageSection(match, imageSection).doubleClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse double click on Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public ListBoxValidatorSI rightClick(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.rightClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse right click on Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	public ListBoxValidatorSI rightClick(ImageSection imageSection, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			getImageSection(match, imageSection).rightClick();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse right click on Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public ListBoxValidatorSI clickAndHold(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.mouseDown(Button.LEFT);
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse clickAndHold on Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public ListBoxValidatorSI release(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.mouseDown(Button.LEFT);
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse clickAndHold on Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}
	
	@Override
	public ListBoxValidatorSI mouseHoverOver(int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.mouseMove();
		} catch (Throwable th) {
			Assert.fail("Failed to perform mouse hoverover on Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public ListBoxValidatorSI performKeyDown(Keys keys, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.click();
			match.keyDown(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyDown on Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public ListBoxValidatorSI performKeyUp(Keys keys, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.click();
			match.keyUp(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyUp ('" + seleniumToSikuliKeyConverter(keys) + "') on Choices '"
					+ listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public ListBoxValidatorSI performKeyPressed(Keys keys, int maxIterationsToLocateElements) {
		try {
			Match match = findElement(maxIterationsToLocateElements);
			match.click();
			match.type(seleniumToSikuliKeyConverter(keys));
		} catch (Throwable th) {
			Assert.fail("Failed to perform keyPressed ('" + seleniumToSikuliKeyConverter(keys) + "') on Choices '"
					+ listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	@Override
	public ListBoxValidatorSI typeText(String text, NewTextLocation location, int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);
		try {
			match.click();
			switch (location) {
			case start:
				match.type(Key.HOME);
				break;
			case end:
				match.type(Key.END);
				break;
			case replace:
				match.type("a", KeyModifier.CTRL);
				break;
			}

			match.type(text);
		} catch (Throwable th) {
			Assert.fail("Fail to type text '" + text + "' in Choices '" + listBoxObj.getDisplayName() + "'.");
		}
		return this;
	}

	@Override
	public ListBoxValidatorSI scrollElementOnViewport(Scrollbar scrollbar) {
		// TODO
		return this;
	}

	@Override
	public Match findElement(int maxIterationsToLocateElements) {
		Match match = null;
		for (int i = 0; i <= maxIterationsToLocateElements; i++) {
			try {
				Region region = listBoxObj.getLocation().getRegionOfImageObject(appDriver, listBoxObj.getWidth(),
						listBoxObj.getHeight());
				Assert.assertNotNull(region, "Failed to find Choices '" + listBoxObj.getDisplayName() + "'.");
				match = new Match(region, 1);
				break;
			} catch (Throwable th) {
				if (i == maxIterationsToLocateElements) {
					Assert.fail("Unable to find Choices '" + listBoxObj.getDisplayName()
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
		Assert.fail("findElements() api for ListBoxSI element is not implemented.");
		return null;
	}

	public ListBoxValidatorSI dragAndDrop(ImageObject target, Region targetRegion, int maxIterationsToLocateElements) {
		try {
			Match sourceElem = findElement(maxIterationsToLocateElements);
			Match targetElem = target.getValidator(appDriver, targetRegion).findElement(maxIterationsToLocateElements);

			Assert.assertNotNull(sourceElem, "Failed to find Choices '" + listBoxObj.getDisplayName() + "'.");
			Assert.assertNotNull(targetElem, "Failed to find element '" + target.getDisplayName() + "'.");

			sourceElem.drag(targetElem);
			sourceElem.dropAt(targetElem);
		} catch (Throwable th) {
			Assert.fail("Failed to perform dragAndDrop from source '" + listBoxObj.getDisplayName() + "' to target '"
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
	public ListBoxValidatorSI validateSelectedItem(String expectedValue, TextMatchMechanism validationMechanism,
			int maxIterationsToLocateElements) {
		if (listBoxObj.isDisabled()) {
			Match match = findElement(maxIterationsToLocateElements);
			validateTextValue(match.text(), expectedValue, validationMechanism);
		} else {
			validateTextValue(getSelectedItem(maxIterationsToLocateElements), expectedValue, validationMechanism);
		}
		return this;
	}

	/**
	 * Used to return value using clipboard method.
	 */
	@Override
	public String getSelectedItem(int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);
		if (listBoxObj.isDisabled()) {
			return match.text();
		} else {
			match.click();

			match.type("ac", KeyModifier.CTRL);

			String contents = ClipboardUtil.getContents();
			ClipboardUtil.clearContents();

			match.click();
			return contents;
		}

	}

	@Override
	public List<String> getSelectedItems(int maxIterationsToLocateElements) {
		Assert.fail("getSelectedItems() API is not implemented.");
		return null;
	}

	@Override
	public ListBoxValidatorSI selectFirstItem(int maxIterationsToLocateElements) {
		Assert.fail("selectFirstItem() API is not implemented.");
		return this;
	}

	@Override
	public ListBoxValidatorSI selectLastItem(int maxIterationsToLocateElements) {
		Assert.fail("selectLastItem() API is not implemented.");
		return this;
	}

	/**
	 * It just search the text in pull down menu visible area if present then click
	 * on it to select that.
	 */
	@Override
	public ListBoxValidatorSI selectItem(String itemName, int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);

		try {
			Match menuItemMatch = match.findBest(itemName);
			Assert.assertNotNull(menuItemMatch,
					"Failed to find item '" + itemName + "' in Choices '" + listBoxObj.getDisplayName() + "'.");
			menuItemMatch.click();
		} catch (Throwable th) {
			Assert.fail("Failed to find item '" + itemName + "' in Choices '" + listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	/**
	 * It just search the image item in pull down menu visible area if present then
	 * click on it to select that.
	 */
	public ListBoxValidatorSI selectItemByImage(String imageItem, int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);

		try {
			Match menuItemMatch = match.findBest(imageItem);
			Assert.assertNotNull(menuItemMatch,
					"Failed to find item '" + imageItem + "' in Choices '" + listBoxObj.getDisplayName() + "'.");
			menuItemMatch.click();
		} catch (Throwable th) {
			Assert.fail("Failed to find item '" + imageItem + "' in pull down menu of Choices '"
					+ listBoxObj.getDisplayName() + "'.", th);
		}
		return this;
	}

	/**
	 * It selects only the visible item in the pull down menu.
	 */
	@Override
	public ListBoxValidatorSI selectItems(ItemList<String> itemsToBeSelected, int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);

		String currentItemName = "";
		try {
			for (String itemName : itemsToBeSelected.getItems()) {
				currentItemName = itemName;
				Match menuItemMatch = match.find(itemName);
				Assert.assertNotNull(menuItemMatch,
						"Failed to find item '" + itemName + "' in Choices '" + listBoxObj.getDisplayName() + "'.");
				menuItemMatch.keyDown(Key.CTRL);
				menuItemMatch.click();
				menuItemMatch.keyUp(Key.CTRL);
			}

		} catch (Throwable th) {
			Assert.fail(
					"Failed to find item '" + currentItemName + "' in Choices '" + listBoxObj.getDisplayName() + "'.");
		}
		return this;
	}

	/**
	 * It selects only the visible image items in the pull down menu.
	 */
	public ListBoxValidatorSI selectItemsByImage(ItemList<String> imageItemsToBeSelected, int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);

		String currentImageItem = "";
		try {
			for (String imageItem : imageItemsToBeSelected.getItems()) {
				currentImageItem = imageItem;
				Match menuItemMatch = match.find(imageItem);
				Assert.assertNotNull(menuItemMatch,
						"Failed to find item '" + imageItem + "' in Choices '" + listBoxObj.getDisplayName() + "'.");
				menuItemMatch.keyDown(Key.CTRL);
				menuItemMatch.click();
				menuItemMatch.keyUp(Key.CTRL);
			}

		} catch (Throwable th) {
			Assert.fail(
					"Failed to find item '" + currentImageItem + "' in Choices '" + listBoxObj.getDisplayName() + "'.");
		}
		return this;
	}

	/**
	 * It only checks the item in visible area of pull down menu.
	 */
	@Override
	public ListBoxValidatorSI validateItemsPresent(ItemList<String> items, int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);
		String currentItemName = "";
		try {
			for (String itemName : items.getItems()) {
				currentItemName = itemName;
				Match menuItemMatch = match.find(itemName);
				if (menuItemMatch == null) {
					throw new FindFailed("Found no match.");
				}
			}
		} catch (Throwable th) {
			Assert.fail(
					"Failed to find item '" + currentItemName + "' in Choices '" + listBoxObj.getDisplayName() + "'.",
					th);
		}
		return this;
	}

	/**
	 * It only checks the image items in visible area of pull down menu.
	 */
	public ListBoxValidatorSI validateItemsPresentByImage(ItemList<String> imageItems, int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);

		String currentImageItem = "";
		try {
			for (String imageItem : imageItems.getItems()) {
				currentImageItem = imageItem;
				Match menuItemMatch = match.find(imageItem);
				if (menuItemMatch == null) {
					throw new FindFailed("Found no match.");
				}
			}
		} catch (Throwable th) {
			Assert.fail(
					"Failed to find item '" + currentImageItem + "' in Choices '" + listBoxObj.getDisplayName() + "'.",
					th);
		}
		return this;
	}

	@Override
	public ListBoxValidatorSI validateItemsNotPresent(ItemList<String> items, int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);
		for (String itemName : items.getItems()) {
			try {
				Match menuItemMatch = match.find(itemName);
				Assert.assertNull(menuItemMatch,
						"Item '" + itemName + "' in Choices '" + listBoxObj.getDisplayName() + "' is already present.");
			} catch (FindFailed th) {
				// do not do anything here
			}
		}
		return this;
	}

	public ListBoxValidatorSI validateItemsNotPresentByImage(ItemList<String> imageItems, int maxIterationsToLocateElements) {
		Match match = findElement(maxIterationsToLocateElements);
		for (String imageItem : imageItems.getItems()) {
			try {
				Match menuItemMatch = match.find(imageItem);
				Assert.assertNull(menuItemMatch, "Item '" + imageItem + "' in Choices '" + listBoxObj.getDisplayName()
						+ "' is already present.");
			} catch (FindFailed th) {
				// do not do anything here
			}
		}
		return this;
	}

	@Override
	@Deprecated
	public boolean isDisabled(int maxIterationsToLocateElements) {
		Assert.fail("isDisabled() API is not supported by ListBox component.");
		return false;
	}

	@Override
	@Deprecated
	public Actions getNewSeleniumActions() {
		Assert.fail("getNewSeleniumActions() API is not supported by ListBox component.");
		return null;
	}

	@Override
	public boolean isDisabledButNotReadonly(int maxIterationsToLocateElements) {
		Assert.fail("isDisabledButNotReadonly() API is not supported by ListBox component.");
		return false;
	}

	@Override
	public ListBoxValidatorSI validateDisabledButNotReadonly(int maxIterationsToLocateElements) {
		Assert.fail("validateDisabledButNotReadonly() API is not supported by ListBox component.");
		return this;
	}

	@Override
	public ListBoxValidatorSI validateEnabledButNotReadonly(int maxIterationsToLocateElements) {
		Assert.fail("validateEnabledButNotReadonly() API is not supported by ListBox component.");
		return this;
	}

	@Override
	public ListBoxValidatorSI selectAllItems(int maxIterationsToLocateElements) {
		Assert.fail("selectAllItems() API is not supported by ListBox component.");
		return this;
	}

	@Override
	public ListBoxValidatorSI deselectItem(String itemName, int maxIterationsToLocateElements) {
		Assert.fail("deselectItem() API is not supported by ListBox component.");
		return this;
	}

	@Override
	public ListBoxValidatorSI deselectAllItems(int maxIterationsToLocateElements) {
		Assert.fail("deselectAllItems() API is not supported by ListBox component.");
		return this;
	}

	@Override
	public ListBoxValidatorSI deselectItems(ItemList<String> itemsToBeDeselected, int maxIterationsToLocateElements) {
		Assert.fail("deselectItems() API is not supported by ListBox component.");
		return this;
	}

	@Override
	public ListBoxValidatorSI selectItem(String itemName, TextMatchMechanism textMatchMechanism,
			int maxIterationsToLocateElements) {
		Assert.fail("selectItem() API is not supported by ListBox component.");
		return this;
	}

	@Override
	public ListBoxValidatorSI selectItems(ItemList<String> itemsToBeSelected, TextMatchMechanism textMatchMechanism,
			int maxIterationsToLocateElements) {
		Assert.fail("selectItems() API is not supported by ListBox component.");
		return this;
	}

	@Override
	public ListBoxValidatorSI deselectItem(String itemName, TextMatchMechanism textMatchMechanism,
			int maxIterationsToLocateElements) {
		Assert.fail("deselectItem() API is not supported by ListBox component.");
		return this;
	}

	@Override
	public ListBoxValidatorSI deselectItems(ItemList<String> itemsToBeDeselected, TextMatchMechanism textMatchMechanism,
			int maxIterationsToLocateElements) {
		Assert.fail("deselectItems() API is not supported by ListBox component.");
		return this;
	}
}
