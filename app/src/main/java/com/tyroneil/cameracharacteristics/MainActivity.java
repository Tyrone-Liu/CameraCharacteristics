package com.tyroneil.cameracharacteristics;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Size;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        textView.setText(gatherCameraCharacteristics());
    }

    private String gatherCameraCharacteristics() {
        CameraManager cameraManager = (CameraManager) this.getSystemService(CameraManager.class);
        String camChaOut = "";
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            camChaOut += "Camera Id List: {";
            for (String e : cameraIdList) {
                camChaOut += e + ", ";
            } camChaOut += "}\n" + "\n\n";

            /**required information
             *
             * # shutter parameters
             * SENSOR_INFO_EXPOSURE_TIME_RANGE (nanosecond)
             * SENSOR_INFO_SENSITIVITY_RANGE
             * LENS_INFO_AVAILABLE_APERTURES {float[]}
             *
             * LENS_INFO_FOCUS_DISTANCE_CALIBRATION
             * LENS_INFO_HYPERFOCAL_DISTANCE (units of diopter)
             * LENS_INFO_MINIMUM_FOCUS_DISTANCE (units of diopter)
             *
             * LENS_INFO_AVAILABLE_FOCAL_LENGTHS (millimeter) {float[]}
             *
             * # hardware information
             * INFO_SUPPORTED_HARDWARE_LEVEL
             * REQUEST_AVAILABLE_CAPABILITIES {int[]}
             * LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION {int[]}
             *
             * SENSOR_INFO_PHYSICAL_SIZE (millimeter)
             * SENSOR_INFO_PIXEL_ARRAY_SIZE (pixel)
             * SENSOR_INFO_ACTIVE_ARRAY_SIZE {Rect}
             * SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE {Rect}
             *
             * # other
             * LENS_FACING
             * SENSOR_ORIENTATION
             * SCALER_STREAM_CONFIGURATION_MAP {StreamConfigurationMap}
             *
             */
            for (String cameraId : cameraIdList) {
                CameraCharacteristics camCha = cameraManager.getCameraCharacteristics(cameraId);
                camChaOut += (
                        "Camera Id: " + cameraId + "\n"

                        + repStr(" ", 2 * 1) + "# Shutter Parameters #" + "\n"
                        + repStr(" ", 2 * 2) + "SENSOR_INFO_EXPOSURE_TIME_RANGE (nanosecond): " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE) + "\n"
                        + repStr(" ", 2 * 2) + "SENSOR_INFO_SENSITIVITY_RANGE: " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE) + "\n"
                        + repStr(" ", 2 * 2) + "LENS_INFO_AVAILABLE_APERTURES: " + "\n" + repStr(" ", 2 * 3) + traFloArr(camCha.get(CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES)) + "\n"
                        + "\n"
                        + repStr(" ", 2 * 2) + "LENS_INFO_FOCUS_DISTANCE_CALIBRATION: " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.LENS_INFO_FOCUS_DISTANCE_CALIBRATION) + "\n"
                        + repStr(" ", 2 * 2) + "LENS_INFO_HYPERFOCAL_DISTANCE (units of diopter): " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.LENS_INFO_HYPERFOCAL_DISTANCE) + "\n"
                        + repStr(" ", 2 * 2) + "LENS_INFO_MINIMUM_FOCUS_DISTANCE (units of diopter): " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE) + "\n"
                        + "\n"
                        + repStr(" ", 2 * 2) + "LENS_INFO_AVAILABLE_FOCAL_LENGTHS (millimeter): " + "\n" + repStr(" ", 2 * 3) + traFloArr(camCha.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS)) + "\n"
                        + "\n"

                        + repStr(" ", 2 * 1) + "# Hardware Information #" + "\n"
                        + repStr(" ", 2 * 2) + "INFO_SUPPORTED_HARDWARE_LEVEL: " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL) + "\n"
                        + repStr(" ", 2 * 2) + "REQUEST_AVAILABLE_CAPABILITIES: " + "\n" + repStr(" ", 2 * 3) + traIntArr(camCha.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES)) + "\n"
                        + repStr(" ", 2 * 2) + "LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION: " + "\n" + repStr(" ", 2 * 3) + traIntArr(camCha.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION)) + "\n"
                        + "\n"
                        + repStr(" ", 2 * 2) + "SENSOR_INFO_PHYSICAL_SIZE (millimeter): " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE) + "\n"
                        + repStr(" ", 2 * 2) + "SENSOR_INFO_PIXEL_ARRAY_SIZE (pixel): " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE) + "\n"
                        + repStr(" ", 2 * 2) + "SENSOR_INFO_ACTIVE_ARRAY_SIZE: " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE) + "\n"
                        + repStr(" ", 2 * 2) + "SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE: " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE) + "\n"
                        + "\n"

                        + repStr(" ", 2 * 1) + "# Other #" + "\n"
                        + repStr(" ", 2 * 2) + "LENS_FACING: " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.LENS_FACING) + "\n"
                        + repStr(" ", 2 * 2) + "SENSOR_ORIENTATION: " + "\n" + repStr(" ", 2 * 3) + camCha.get(CameraCharacteristics.SENSOR_ORIENTATION) + "\n"
                );

                // transfer SCALER_STREAM_CONFIGURATION_MAP data into plan text
                camChaOut += (
                        repStr(" ", 2 * 2) + "SCALER_STREAM_CONFIGURATION_MAP: " + "\n"
                        + repStr(" ", 2 * 3) + "Output Formats: " + "\n"
                        + repStr(" ", 2 * 4) + traIntArr((camCha.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputFormats()) + "\n"
                );
                int counter = 0;
                camChaOut += repStr(" ", 2 * 3) + "Output Sizes (RAW_SENSOR): {" + "\n";
                for (Size e : (camCha.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(32)) {
                    camChaOut += (
                            repStr(" ", 2 * 4) + String.format("%3d", counter) + ". " + e
                            + "  " + String.format("%.2f", (float) (e.getWidth() * e.getHeight()) / 1000000) + "MP"
                            + "  " + String.format("%.4f", (float) e.getWidth() / e.getHeight()) + "W/H" + "\n"
                    ); counter ++;
                } camChaOut += repStr(" ", 2 * 4) + "}\n";
                counter = 0;
                camChaOut += repStr(" ", 2 * 3) + "Output Sizes (JPEG): {" + "\n";
                for (Size e : (camCha.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(256)) {
                    camChaOut += (
                            repStr(" ", 2 * 4) + String.format("%3d", counter) + ". " + e
                            + "  " + String.format("%.2f", (float) (e.getWidth() * e.getHeight()) / 1000000) + "MP"
                            + "  " + String.format("%.4f", (float) e.getWidth() / e.getHeight()) + "W/H" + "\n"
                    ); counter ++;
                } camChaOut += repStr(" ", 2 * 4) + "}\n";
                counter = 0;
                camChaOut += repStr(" ", 2 * 3) + "Output Sizes (SurfaceTexture): {" + "\n";
                for (Size e : (camCha.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(SurfaceTexture.class)) {
                    camChaOut += (
                            repStr(" ", 2 * 4) + String.format("%3d", counter) + ". " + e
                            + "  " + String.format("%.2f", (float) (e.getWidth() * e.getHeight()) / 1000000) + "MP"
                            + "  " + String.format("%.4f", (float) e.getWidth() / e.getHeight()) + "W/H" + "\n"
                    ); counter ++;
                } camChaOut += repStr(" ", 2 * 4) + "}\n";

                camChaOut += "\n\n";
            }
        } catch (CameraAccessException e) {
            e.getStackTrace();
        }
        return camChaOut;
    }

    private static String traFloArr(float[] camChaValue) {
        String output = "{";
        for (float e : camChaValue) {
            output += e + ", ";
        } output += "}";
        return output;
    }

    private static String traIntArr(int[] camChaValue) {
        String output = "{";
        for (int e : camChaValue) {
            output += e + ", ";
        } output += "}";
        return output;
    }

    private static String repStr(String str, int count) {
        String repeatedString = "";
        for (int i = 0; i < count; i += 1) {
            repeatedString += str;
        }
        return repeatedString;
    }
}
