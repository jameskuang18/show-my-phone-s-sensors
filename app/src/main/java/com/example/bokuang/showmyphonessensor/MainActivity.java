package com.example.bokuang.showmyphonessensor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_sensor;
    public SensorManager mSensroMgr;//获得安卓硬件传感器框架
    private String[] mSensorType = {
            "加速度", "磁场", "方向", "陀螺仪", "光线",
            "压力", "温度", "距离", "重力", "线性加速度",
            "旋转矢量", "湿度", "环境温度", "无标定磁场", "无标定旋转矢量",
            "未校准陀螺仪", "特殊动作", "步行检测", "计步器", "地磁旋转矢量",
            "心跳", "倾斜检测", "唤醒手势", "瞥一眼", "捡起来"};
    private Map<Integer, String> mapSensor = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_sensor = (TextView) findViewById(R.id.tv_sensor);
    showSensorInfo();
   //SensorManager.getDefaultSensor(mSensorType);
}

    private void showSensorInfo() {
        mSensroMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = mSensroMgr.getSensorList(Sensor.TYPE_ALL);//存入传感器容器
        String show_content = "当前支持的传感器包括：\n";
        int i=0;
        for (Sensor sensor : sensorList) {
            //getType获取传感器ID，根据ID传入getDefaultSensor获取传感器类型，非唤醒/唤醒
            if (sensor.getType() >= mSensorType.length) {
                continue;
            }
            mapSensor.put(sensor.getType(), sensor.getName());
        }
        for (Map.Entry<Integer, String> item_map : mapSensor.entrySet()) {
            int type = item_map.getKey();
            String name = item_map.getValue();
            String content = String.format("%d %s：%s\n", type, mSensorType[type - 1], name);//获取传感器信息
            System.out.println("具体信息: "+mSensroMgr.getDefaultSensor(type));
            System.out.println("\r");
            show_content += content;
        }
        tv_sensor.setText(show_content);
    }
}
