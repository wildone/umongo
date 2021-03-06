/**
 *      Copyright (C) 2010 EdgyTech Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.edgytech.umongo;

import com.edgytech.swingfast.FieldChecker;
import com.edgytech.swingfast.XmlUnitField;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 *
 * @author antoine
 */
public class EditPatternDialog extends EditFieldDialog implements FieldChecker {

    enum Item {
        value
    }

    public EditPatternDialog() {
        setEnumBinding(Item.values(), null);
        setFieldChecker(this);
    }

    @Override
    public Object getValue() {
        String str = getStringFieldValue(Item.value);
        return Pattern.compile(str);
    }

    @Override
    public void setValue(Object value) {
        Pattern p = (Pattern) value;
        setStringFieldValue(Item.value, p.toString());
    }

    public boolean formCheckField(Enum enm, XmlUnitField field) {
        if (enm == Item.value) {
            try {
                Pattern.compile(getComponentStringFieldValue(Item.value));
                return true;
            } catch (Exception e) {
                getLogger().log(Level.WARNING, null, e);
            }
            field.setDisplayError("Cannot compile pattern");
            return false;
        }
        return true;
    }

}
