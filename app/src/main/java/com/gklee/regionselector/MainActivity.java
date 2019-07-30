package com.gklee.regionselector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<RegionBean> provinceBeans;
    private String mProvince;
    private String mCity;
    private String mZone;
    private RegionSelectDialog regionSelectDialog;
    private String mArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv_region=findViewById(R.id.tv_region);

        //初始化时，传入RegionLevel设置三级联动or四级联动）
        regionSelectDialog = new RegionSelectDialog(this,RegionLevel.LEVEL_FOUR);
//        regionSelectDialog.setLevel();
        //获取省级数据
        provinceBeans = new ArrayList<>();
        RegionBean temp=new RegionBean("1","广东省");
        provinceBeans.add(temp);
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
        tv_region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示
                regionSelectDialog.showDialog();
            }
        });

    }
}
