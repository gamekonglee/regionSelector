package com.gklee.regionselector;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public  class RegionSelectDialog  {

    private final Activity mContext;
    private final Dialog dialog;
    private final TextView tv_province;
    private final ImageView iv_back;
    private final TextView tv_city;
    private final TextView tv_zone_dialog;
    private final TextView tv_area;
    private final ListView lv_province;
    private final ListView lv_city;
    private final ListView lv_zone;
    private final ListView lv_area;
    private List<RegionBean> proince=new ArrayList<>();
    private QuickAdapter<RegionBean> provinceAdapter;
    private final QuickAdapter cityAdapter;
    private final QuickAdapter zoneAdapter;
    private final QuickAdapter areaAdapter;
    private OnRegionDataSetListener mProvinDataListenr;
    private List<RegionBean> cityList;
    private List<RegionBean> zoneList;
    private List<RegionBean> areaList;
    private final RegionLevel regionLevel;
    private final boolean isFour;

    public RegionSelectDialog(Activity context, RegionLevel levelFour){
        mContext = context;

        dialog = new Dialog(mContext,R.style.regionsDialog);
        dialog.setContentView(R.layout.dialog_address_b);
        dialog.setCanceledOnTouchOutside(true);
        tv_province = (TextView) dialog.findViewById(R.id.tv_province);
        iv_back = (ImageView) dialog.findViewById(R.id.iv_back);
        tv_city = (TextView) dialog.findViewById(R.id.tv_city);
        tv_zone_dialog = (TextView) dialog.findViewById(R.id.tv_zone_dialog);
        tv_area = dialog.findViewById(R.id.tv_area_dialog);

        lv_province = (ListView) dialog.findViewById(R.id.lv_address);
        lv_city = (ListView) dialog.findViewById(R.id.lv_city);
        lv_zone = (ListView) dialog.findViewById(R.id.lv_zone);
        lv_area = dialog.findViewById(R.id.lv_area);
        regionLevel = levelFour;
        if (regionLevel.equals(RegionLevel.LEVEL_FOUR)) {
            isFour = true;
            tv_area.setVisibility(View.INVISIBLE);
            lv_area.setVisibility(View.INVISIBLE);
        }else {
            isFour=false;
            tv_area.setVisibility(View.GONE);
            lv_area.setVisibility(View.GONE);
        }
        provinceAdapter = new QuickAdapter<RegionBean>(mContext,R.layout.item_address_dialog) {
            @Override
            protected void convert(BaseAdapterHelper helper, RegionBean item) {
                helper.setText(R.id.tv_item,item.getName());
            }
        };
        cityAdapter = new QuickAdapter<RegionBean>(mContext,R.layout.item_address_dialog) {
            @Override
            protected void convert(BaseAdapterHelper helper, RegionBean item) {
                helper.setText(R.id.tv_item,item.getName());
            }
        };
        zoneAdapter = new QuickAdapter<RegionBean>(mContext,R.layout.item_address_dialog) {
            @Override
            protected void convert(BaseAdapterHelper helper, RegionBean item) {
                helper.setText(R.id.tv_item,item.getName());
            }
        };
        areaAdapter = new QuickAdapter<RegionBean>(mContext,R.layout.item_address_dialog) {
            @Override
            protected void convert(BaseAdapterHelper helper, RegionBean item) {
                helper.setText(R.id.tv_item,item.getName());
            }
        };
        lv_province.setAdapter(provinceAdapter);
        lv_city.setAdapter(cityAdapter);
        lv_zone.setAdapter(zoneAdapter);
        lv_area.setAdapter(areaAdapter);

        tv_province.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_province.setVisibility(View.VISIBLE);
                lv_zone.setVisibility(View.GONE);
                lv_city.setVisibility(View.GONE);
                lv_area.setVisibility(View.GONE);
                tv_province.setTextColor(mContext.getResources().getColor(R.color.theme_red));
                tv_city.setTextColor(mContext.getResources().getColor(R.color.region_black));
                tv_zone_dialog.setTextColor(mContext.getResources().getColor(R.color.region_black));
                tv_area.setTextColor(mContext.getResources().getColor(R.color.region_black));
            }
        });
        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_province.setVisibility(View.GONE);
                lv_zone.setVisibility(View.GONE);
                lv_city.setVisibility(View.VISIBLE);
                lv_area.setVisibility(View.GONE);
                tv_province.setTextColor(mContext.getResources().getColor(R.color.region_black));
                tv_city.setTextColor(mContext.getResources().getColor(R.color.theme_red));
                tv_zone_dialog.setTextColor(mContext.getResources().getColor(R.color.region_black));
                tv_area.setTextColor(mContext.getResources().getColor(R.color.region_black));
            }
        });
        tv_zone_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_city.setVisibility(View.GONE);
                lv_zone.setVisibility(View.VISIBLE);
                lv_province.setVisibility(View.GONE);
                lv_area.setVisibility(View.GONE);
                tv_province.setTextColor(mContext.getResources().getColor(R.color.region_black));
                tv_city.setTextColor(mContext.getResources().getColor(R.color.region_black));
                tv_zone_dialog.setTextColor(mContext.getResources().getColor(R.color.theme_red));
                tv_area.setTextColor(mContext.getResources().getColor(R.color.region_black));
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tv_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_city.setVisibility(View.GONE);
                lv_zone.setVisibility(View.GONE);
                lv_province.setVisibility(View.GONE);
                lv_area.setVisibility(View.VISIBLE);
                tv_province.setTextColor(mContext.getResources().getColor(R.color.region_black));
                tv_city.setTextColor(mContext.getResources().getColor(R.color.region_black));
                tv_area.setTextColor(mContext.getResources().getColor(R.color.theme_red));
                tv_zone_dialog.setTextColor(mContext.getResources().getColor(R.color.region_black));
            }
        });

        lv_province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        if(mProvinDataListenr!=null){
                            cityList = mProvinDataListenr.setOnProvinceSelected(proince.get(position));
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(cityList==null||cityList.size()==0){
                                        Toast.makeText(mContext,"没有获取到该省份的城市数据",Toast.LENGTH_LONG);
                                        return;
                                    }
                                    tv_province.setText(proince.get(position).getName());
                                    tv_province.setEnabled(true);
                                    tv_city.setVisibility(View.VISIBLE);
                                    tv_province.setTextColor(mContext.getResources().getColor(R.color.region_black));
                                    tv_city.setTextColor(mContext.getResources().getColor(R.color.theme_red));
                                    tv_zone_dialog.setTextColor(mContext.getResources().getColor(R.color.region_black));
                                    tv_area.setTextColor(mContext.getResources().getColor(R.color.region_black));
//                                            setCityAdapter();
                                    cityAdapter.replaceAll(cityList);
                                    lv_province.setVisibility(View.GONE);
                                    lv_zone.setVisibility(View.GONE);
                                    lv_area.setVisibility(View.GONE);
                                    lv_city.setVisibility(View.VISIBLE);
                                    lv_city.setAdapter(cityAdapter);
                                }
                            });
                        }
                    }
                }.start();

            }
        });
        lv_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        if(mProvinDataListenr!=null){
                            zoneList = mProvinDataListenr.setOnCitySelected(cityList.get(position));
//                    mCity=cityList.get(position).getId();
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(zoneList==null||zoneList.size()==0){
                                        Toast.makeText(mContext,"没有获取到该城市的区域数据",Toast.LENGTH_LONG);
                                        return;
                                    }
                                    zoneAdapter.replaceAll(zoneList);
                                    tv_city.setText(cityList.get(position).getName());
                                    tv_city.setTextColor(mContext.getResources().getColor(R.color.region_black));
                                    tv_province.setTextColor(mContext.getResources().getColor(R.color.region_black));
                                    tv_area.setTextColor(mContext.getResources().getColor(R.color.region_black));
                                    tv_zone_dialog.setTextColor(mContext.getResources().getColor(R.color.theme_red));
                                    tv_zone_dialog.setVisibility(View.VISIBLE);
                                    tv_city.setEnabled(true);
                                    lv_province.setVisibility(View.GONE);
                                    lv_zone.setVisibility(View.VISIBLE);
                                    lv_city.setVisibility(View.GONE);
                                    lv_area.setVisibility(View.GONE);
                                }
                            });
                        }
                    }
                }.start();

            }
        });
        lv_zone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        if(mProvinDataListenr!=null){
                            areaList = mProvinDataListenr.setOnZoneSelected(zoneList.get(position));
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(areaList==null||areaList.size()==0||!isFour){
                                        dialog.dismiss();
                                        return;
                                    }
                                    tv_zone_dialog.setText(zoneList.get(position).getName());
                                    tv_zone_dialog.setTextColor(mContext.getResources().getColor(R.color.region_black));
                                    tv_province.setTextColor(mContext.getResources().getColor(R.color.region_black));
                                    tv_area.setTextColor(mContext.getResources().getColor(R.color.region_black));
                                    tv_area.setTextColor(mContext.getResources().getColor(R.color.theme_red));
                                    if(isFour){
                                        tv_area.setVisibility(View.VISIBLE);
                                    }
                                    tv_zone_dialog.setEnabled(true);
                                    lv_province.setVisibility(View.GONE);
                                    lv_zone.setVisibility(View.GONE);
                                    lv_city.setVisibility(View.GONE);
                                    lv_area.setVisibility(View.VISIBLE);
                                    areaAdapter.replaceAll(areaList);
                                }
                            });
                        }
                    }
                }.start();

            }
        });
        lv_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mProvinDataListenr!= null){
                    mProvinDataListenr.setOnAreaSelected(areaList.get(i));
                }
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
        });

    }
    public  void setProvinceData(List<RegionBean> regionBeans){
        proince = regionBeans;
        if(proince!=null&&proince.size()>0){
            provinceAdapter.replaceAll(proince);
        }
    }
    public Dialog showDialog(){
        if(mProvinDataListenr==null)return null;

        setProvinceData(mProvinDataListenr.setProvinceList());
        if(proince==null||proince.size()<=0){
            Toast.makeText(mContext,"请先初始化数据",Toast.LENGTH_LONG);
            return null;
        }
        Window win = dialog.getWindow();
        win.setGravity(Gravity.BOTTOM );
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = mContext.getResources().getDisplayMetrics().heightPixels/2;
        win.setWindowAnimations(R.style.dialogButtomInStyle);
        win.setAttributes(lp);
        dialog.show();
        return dialog;
    }
    public void setOnRegionDataSetListenr(OnRegionDataSetListener provinceDataSetListenr){
        mProvinDataListenr = provinceDataSetListenr;
    }

//    public void setLevel(RegionLevel levelFour) {
//
//    }
}
