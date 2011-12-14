package com.ubn.befamous.util;

import java.util.ArrayList;
import java.util.Set;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.ShoppingCartDetail;
import com.ubn.befamous.entity.Song;

public class CheckPriceUtil {
	
	public static ArrayList<Integer> checkPrice(ShoppingCartDetail[] songDetail,ShoppingCartDetail[] albumDetail){
		int tSongPrice = 0;
		int tSongBonus = 0;
		int tAlbumPrice = 0;
		int tAlbumBonus = 0;
		for(ShoppingCartDetail d : songDetail){
			if(d.getProductionCategory() instanceof Song){
				Song s = (Song)d.getProductionCategory();
				String price = "0";
				String bonus = "0";
				if("N".equals(d.getUseBonus())){
				      price = s.getSongPrice().getPrice();
				}else{
					  price = s.getSongPrice().getDiscountPrice();
					  bonus = s.getSongPrice().getDiscountBonus();
				}
				tSongPrice += Integer.parseInt(price);
				tSongBonus += Integer.parseInt(bonus);
			}	
		}
		
		for(ShoppingCartDetail d : albumDetail){
			if(d.getProductionCategory() instanceof Album){
				Album a = (Album)d.getProductionCategory();
				
				Set<Song> songSet = a.getSongSet();
				if("N".equals(d.getUseBonus())){
				     for(Song s : songSet){
				    	 tAlbumPrice += Integer.parseInt(s.getSongPrice().getPrice());
				     }	
				}else{
					 for(Song s : songSet){
						 tAlbumPrice += Integer.parseInt(s.getSongPrice().getDiscountPrice());
						 tAlbumBonus += Integer.parseInt(s.getSongPrice().getDiscountBonus());
				     }	
				}
				
			}
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(tSongPrice);
		list.add(tSongBonus);
		list.add(tAlbumPrice);
		list.add(tAlbumBonus);
		
		return list;
	}

	
}
