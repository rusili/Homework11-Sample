package com.example.rusili.homework11.common;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.View;

import com.example.rusili.homework11.R;

public abstract class FragmentAbstractActivity extends AppCompatActivity {
	public final FragmentManager fragmentManager = getSupportFragmentManager();
	private final LoadingFragment loadingFragment = new LoadingFragment();

	public View container;

	@Override
	public void onCreate (@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abstract_activity_layout);

		setContainer();
		setLoadingTransition();
	}

	private void setLoadingTransition () {
		if (Build.VERSION.SDK_INT >= 19){
			Fade fade = new Fade();
			fade.setDuration(250);
			loadingFragment.setExitTransition(fade);
		}
	}

	public void showFragment(AbstractFragment abstractFragment){
		fragmentManager.beginTransaction()
			  .replace(getContainerId(), abstractFragment)
			  .addToBackStack(null)
			  .commit();
	}

	public int getContainerId(){
		return container.getId();
	}

	private void setContainer () {
		this.container = findViewById(R.id.container);
	}

	public void showLoadingFragment () {
		fragmentManager.beginTransaction()
			  .add(container.getId(), loadingFragment)
			  .commit();
	}

	public void hideLoadingFragment(){
		fragmentManager.beginTransaction()
			  .remove(loadingFragment)
			  .commit();
	}
}
