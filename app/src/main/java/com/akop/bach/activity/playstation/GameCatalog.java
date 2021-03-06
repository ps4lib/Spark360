/*
 * GameCatalog.java 
 * Copyright (C) 2010-2014 Akop Karapetyan
 *
 * This file is part of Spark 360, the online gaming service client.
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 *  02111-1307  USA.
 *
 */

package com.akop.bach.activity.playstation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.akop.bach.PSN.GameCatalogItem;
import com.akop.bach.PsnAccount;
import com.akop.bach.R;
import com.akop.bach.fragment.playstation.GameCatalogDetailsFragment;
import com.akop.bach.fragment.playstation.GameCatalogFragment;
import com.akop.bach.fragment.playstation.GameCatalogFragment.OnItemSelectedListener;

public class GameCatalog extends PsnMultiPane implements
        OnItemSelectedListener
{
	@Override
	protected Fragment instantiateDetailFragment()
	{
		return GameCatalogDetailsFragment.newInstance(getAccount());
	}
	
	@Override
	protected Fragment instantiateTitleFragment()
	{
		return GameCatalogFragment.newInstance(getAccount());
	}
	
	public static void actionShow(Context context, PsnAccount account)
	{
		Intent intent = new Intent(context, GameCatalog.class);
		intent.putExtra("account", account);
		context.startActivity(intent);
	}
	
	@Override
	protected String getSubtitle()
	{
		return getString(R.string.game_catalog);
	}
	
	@Override
	public void onItemSelected(GameCatalogItem item)
	{
		if (isDualPane())
		{
			GameCatalogDetailsFragment detailFragment = (GameCatalogDetailsFragment)mDetailFragment;
			detailFragment.resetTitle(item);
		}
		else
		{
			if (item == null || item.DetailUrl == null)
				return;
			
			GameCatalogDetails.actionShow(this, getAccount(), item);
		}
	}
}
