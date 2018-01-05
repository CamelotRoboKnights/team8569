package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by christopherradcliffe on 1/4/18.
 */

public class Sensors {
    public static class Phone {
        public static final String TAG = "Vuforia VuMark Sample";
        OpenGLMatrix lastLocation = null;


        VuforiaLocalizer vuforia;
        VuforiaTrackables relicTrackables;
        VuforiaTrackable relicTemplate;


        //initializes camera
        public void initCamera() {

            //int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            //VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

            parameters.vuforiaLicenseKey = "AdPKKrD/////AAAAGSIjugGbVECsk0zYoRryiTAFXRdUl/55VWc+O8yUqpFWWbX5fb+/Zve64dxPz4vsZL0Rd4TtPzzSTGDb7NHHnVppmnFp99eLe8jY+q9tvjQ4Iu9kaDaOxTNKRr8kWdWdT7Xa0AksnQ0stzkkHjgxScrOOcA8Poq3+xAEswsM3DW4Di9KeJdQqnX/xa3i5TKzOjO+748hWjwjNcAFoUYjnUbHNp9oYQnYhhiigEHoC0CGAHMTsyYFEKJdwJgcFLsYPqVH/9h/ISSd3saogNwVVpEIVRIu1QL+c7/9h6yKnDdPyV2x1qEZuiXEqTiQJjSt0t3UQ32Q47CO/634+h/VP2HaJHCv9gnJhn7jkRVc6VZA";

            parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
            this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
            relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
            relicTemplate = relicTrackables.get(0);

            relicTrackables.activate();
        }

        //finds out witch vuMark you are seeing
        public String key (){

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                return "" + vuMark;

            }
            else {
                return "null";
            }

        }



    }
    public static class Navx {
        NavxMicroNavigationSensor navx = null;
        Navx (NavxMicroNavigationSensor navx){
            this.navx = navx;
        }
        double currentOrientation = getCurrentOrientation();
        public double getCurrentOrientation () {
            if(!(navx == null)) {
                Orientation navxAngles = navx.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                return -navxAngles.firstAngle;
            } else return 0;
        }
    }
    public static class RevIMU {
        BNO055IMU imu;
        RevIMU (BNO055IMU imu) {
            this.imu = imu;
        }
        double currentOrientation = getCurrentOrientation();
        public double getCurrentOrientation () {
            if(!(imu == null)) {
                Orientation imuAngularOrientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                return imuAngularOrientation.firstAngle;
            } else return 0;
        }
    }
}
