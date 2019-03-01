package org.firstinspires.ftc.teamcode.team.VexEDR393Encoder;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@Disabled
@Autonomous(name = "VEX", group = "VEX")
// @Disabled
public class VexEDR393Test extends OpMode {
    I2cDeviceSynch encoder;
    CRServo servo;

    public void init(){
        encoder = hardwareMap.i2cDeviceSynch.get("encoder");
        servo = hardwareMap.crservo.get("servo");
    }
    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){
        servo.setPower(1);
//        encoder.enableI2cReadMode();
//        if (encoder.isI2cPortReady()) {
//            telemetry.addData("ready", "yeh");
//            if(encoder.isI2cPortInReadMode()) {
//                telemetry.addData("read", "mode");
//                telemetry.addData("buffer", encoder.getCopyOfReadBuffer().toString());
//                telemetry.addData("cashe", encoder.getI2cReadCache().toString());
//            }
//            if(encoder.isI2cPortInWriteMode()) {
//                telemetry.addData("write", "mode");
//            }
//
//        }
//
//

    }
    @Override
    public void stop(){}

}

