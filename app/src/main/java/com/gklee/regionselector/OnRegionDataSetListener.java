package com.gklee.regionselector;

import java.util.List;

public interface OnRegionDataSetListener {
     List<RegionBean> setProvinceList( );
     List<RegionBean> setOnProvinceSelected(RegionBean regionBean);
     List<RegionBean> setOnCitySelected(RegionBean regionBean);
     List<RegionBean> setOnZoneSelected(RegionBean regionBean);
     void setOnAreaSelected(RegionBean regionBean);


}
