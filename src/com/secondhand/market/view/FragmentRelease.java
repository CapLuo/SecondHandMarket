package com.secondhand.market.view;

import android.support.v4.app.Fragment;

public class FragmentRelease extends Fragment {

	protected ChoiceFragmentInterface mChoice = new ChoiceFragmentInterface() {

		@Override
		public void setChoice() {
		}
	};

	public interface ChoiceFragmentInterface {
		public void setChoice();
	}

	public void setChoiceFragmentInterface(ChoiceFragmentInterface inface) {
		mChoice = inface;
	}
}
