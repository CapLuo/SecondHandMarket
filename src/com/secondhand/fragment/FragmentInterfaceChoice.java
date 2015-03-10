package com.secondhand.fragment;

import android.support.v4.app.Fragment;

public class FragmentInterfaceChoice extends Fragment {

	private ChoiceFragmentInterface mChoice = new ChoiceFragmentInterface() {

		@Override
		public void setChoice(int flag) {
		}
	};

	protected void setChoic(int flag) {
		mChoice.setChoice(flag);
	}

	public interface ChoiceFragmentInterface {
		public void setChoice(int flag);
	}

	protected void setChoiceFragmentInterface(ChoiceFragmentInterface inface) {
		mChoice = inface;
	}
}
