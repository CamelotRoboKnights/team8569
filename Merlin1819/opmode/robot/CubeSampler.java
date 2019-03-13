package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

public final class CubeSampler {
    public enum CubePosition {
        LEFT, RIGHT, CENTER
    }

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AdPKKrD/////AAAAGSIjugGbVECsk0zYoRryiTAFXRdUl/55VWc+O8yUqpFWWbX5fb+/Zve64dxPz4vsZL0Rd4TtPzzSTGDb7NHHnVppmnFp99eLe8jY+q9tvjQ4Iu9kaDaOxTNKRr8kWdWdT7Xa0AksnQ0stzkkHjgxScrOOcA8Poq3+xAEswsM3DW4Di9KeJdQqnX/xa3i5TKzOjO+748hWjwjNcAFoUYjnUbHNp9oYQnYhhiigEHoC0CGAHMTsyYFEKJdwJgcFLsYPqVH/9h/ISSd3saogNwVVpEIVRIu1QL+c7/9h6yKnDdPyV2x1qEZuiXEqTiQJjSt0t3UQ32Q47CO/634+h/VP2HaJHCv9gnJhn7jkRVc6VZA";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    private HardwareMap hardwareMap;

    public CubeSampler(HardwareMap hardwareMap, double minimumConfidence) {
        this.hardwareMap = hardwareMap;

        this.initVuforia();
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            this.initTfod(minimumConfidence);
        } else {
            throw new RuntimeException("A TensorFlow object could not be created on this device.");
        }

        this.activateTfod();
    }

    public CubeSampler(HardwareMap hardwareMap) {
        this(hardwareMap, 0.40);
    }

    public CubePosition detectCubePosition() {
        final List<Recognition> updatedRecognitions = this.tfod.getUpdatedRecognitions();
        if (updatedRecognitions != null && updatedRecognitions.size() == 3) {
            int goldMineralX = -1;
            int silverMineral1X = -1;
            int silverMineral2X = -1;
            for (Recognition recognition : updatedRecognitions) {
                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                    goldMineralX = (int) recognition.getLeft();
                } else if (silverMineral1X == -1) {
                    silverMineral1X = (int) recognition.getLeft();
                } else {
                    silverMineral2X = (int) recognition.getLeft();
                }
            }

            if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                    return CubePosition.LEFT;
                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                    return CubePosition.RIGHT;
                } else {
                    return CubePosition.CENTER;
                }
            }
        }

        return null;
    }

    public void activateTfod() {
        this.tfod.activate();
    }

    public void deactivateTfod() {
        this.tfod.deactivate();
    }

    public void shutdownTfod() {
        this.tfod.shutdown();
    }

    private void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        this.vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod(double minimumConfidence) {
        int tfodMonitorViewId = this.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", this.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = minimumConfidence;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, this.vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

}