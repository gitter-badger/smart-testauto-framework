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
package org.uitnet.testing.smartfwk.ui.core.objects.radio;

import org.sikuli.script.Region;
import org.uitnet.testing.smartfwk.ui.core.appdriver.SmartAppDriver;
import org.uitnet.testing.smartfwk.ui.core.commons.ItemMap;
import org.uitnet.testing.smartfwk.ui.core.objects.UIObjectValidator;

/**
 * 
 * @author Madhav Krishna
 *
 */
public abstract class RadioButtonGroupValidator extends UIObjectValidator {
	private RadioButtonGroup optionGroup;

	public RadioButtonGroupValidator(SmartAppDriver appDriver, RadioButtonGroup uiObject, Region region) {
		super(appDriver, uiObject, region);
		this.optionGroup = uiObject;
	}

	@Override
	public RadioButtonGroup getUIObject() {
		return optionGroup;
	}

	public abstract RadioButtonGroupValidator validateDisabled(int maxIterationsToLocateElements);

	public abstract RadioButtonGroupValidator validateEnabled(int maxIterationsToLocateElements);

	public abstract RadioButtonGroupValidator selectOption(String value, String displayValue, int maxIterationsToLocateElements);

	public abstract RadioButtonGroupValidator validateSelectedOption(String value, String displayValue, int maxIterationsToLocateElements);

	/**
	 * 
	 * @param options:   ItemMap contains key: optionValue, value:
	 *                   optionDisplayValue
	 * @param maxIterationsToLocateElements
	 */
	public abstract RadioButtonGroupValidator validateNotSelectedOptions(ItemMap<String, String> options, int maxIterationsToLocateElements);
	
	public abstract RadioButtonGroupValidator validateNoOptionsAreSelected(int maxIterationsToLocateElements);
}
