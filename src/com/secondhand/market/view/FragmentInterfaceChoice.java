package com.secondhand.market.view;

import android.support.v4.app.Fragment;

public class FragmentInterfaceChoice extends Fragment {

	protected ChoiceFragmentInterface mChoice = new ChoiceFragmentInterface() {

		@Override
		public void setChoice(int flag) {
		}
	};

	public interface ChoiceFragmentInterface {
		public void setChoice(int flag);
	}

	public void setChoiceFragmentInterface(ChoiceFragmentInterface inface) {
		mChoice = inface;
	}
}
