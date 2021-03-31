# regionSelector
#一款三级、四级联动（省、市、区、镇）的地区选择器





#########
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
  
  
  
  
  
  
  
  
  ##########
  dependencies {
	        implementation 'com.github.gamekonglee:regionSelector:2.0'
	}



##########
第一步：

 
        //初始化时，传入RegionLevel设置三级联动or四级联动）
        RegionSelectDialog regionSelectDialog = new RegionSelectDialog(this,RegionLevel.LEVEL_FOUR);
##########
第二步：

获取省级数据列表：

      //获取省级数据，此处为测试数据
        provinceBeans = new ArrayList<>();
        RegionBean temp=new RegionBean("1","广东省");
        provinceBeans.add(temp);
#########
第三步：

设置每一次选择后，把下一级的数据传递过去的操作（必要）

 //此步骤必须，否则无法显示
        regionSelectDialog.setOnRegionDataSetListenr(new OnRegionDataSetListener() {
 
            //必须有返回值
            @Override
            public List<RegionBean> setProvinceList() {
                return provinceBeans;
            }
            //必须有返回值
            @Override
            public List<RegionBean> setOnProvinceSelected(RegionBean regionBean) {
 
                //接收选中的省份
                mProvince = regionBean.getName();
                //返回显示的城市
                List<RegionBean> cityBeans=new ArrayList<>();
                cityBeans.add(new RegionBean("2","佛山市"));
                return cityBeans;
            }
            //必须有返回值
            @Override
            public List<RegionBean> setOnCitySelected(RegionBean regionBean) {
                //接收选中的城市
                mCity = regionBean.getName();
                //返回显示的区县
                List<RegionBean> zoneBeans=new ArrayList<>();
                zoneBeans.add(new RegionBean("3","顺德区"));
                return zoneBeans;
            }
            //四级联动时，必须有返回值。三级联动时，可忽略。
            @Override
            public List<RegionBean> setOnZoneSelected(RegionBean regionBean) {
                //接收选中的区县
                mZone = regionBean.getName();
 
                List<RegionBean> zoneList=new ArrayList<>();
                zoneList.add(new RegionBean("4","乐从镇"));
                //返回显示的街道
                //（如果是三级联动，则不需要返回）
                return zoneList;
            }
            @Override
            public void setOnAreaSelected(RegionBean regionBean) {
                //如果是四级联动，则得到选中的街道,如果是三级联动，则没有返回
                mArea = regionBean.getName();
                tv_region.setText(mProvince+" "+mCity+" "+mZone+" "+mArea);
            }
 
        });

############
第四步：直接显示

   regionSelectDialog.showDialog();
