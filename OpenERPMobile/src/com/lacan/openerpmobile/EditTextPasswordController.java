package com.lacan.openerpmobile;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class EditTextPasswordController
{
	private class MyTextWatcher implements TextWatcher
	{
		private EditText caller;

		public MyTextWatcher(EditText caller)
		{
			this.caller = caller;
		}

		@Override
		public void afterTextChanged(Editable s)
		{
			EditText edt = caller;
			if (edt.getText().length() == 0)
				edt.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_NORMAL);
			else
				edt.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			edt.setSelection(edt.getText().length());
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after)
		{
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count)
		{
		}

	}

	public EditTextPasswordController(EditText edittext)
	{
		edittext.addTextChangedListener(new MyTextWatcher(edittext));
		edittext.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			public void onFocusChange(View v, boolean hasFocus)
			{
				EditText etv = (EditText) v;
				if (!hasFocus)
				{
					if (etv.getText().length() != 0)
						etv.setInputType(InputType.TYPE_CLASS_TEXT
								| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					else
						etv.setInputType(InputType.TYPE_CLASS_TEXT
								| InputType.TYPE_TEXT_VARIATION_NORMAL);
				}
			}
		});
	}
}
