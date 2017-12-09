package org.firstinspires.ftc.teamcode.team.Other;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;
import com.qualcomm.robotcore.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.io.StringWriter;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/* Meathods
 * orientation() double - returns the navX orientation
 * revOrientation() double - returns the orientation of the rev device
 * moveMotorPower(M1p, M2p, M3p, M4p) void - set all drive motor power
 * joyValues () double[] - set joystick values
 * makeFieldOriented (origanalxyz, Orientation Degrees) Double [] - orients drivetrain
 * glyph () void - controls glyph mechinism during teleop
 * sorter(String upOrDown, Sring redOrBlue) Boolean - controls the string
 * moveDirection (String direction, Double power) void - gives us comands that can move the robot in a direction
 * currentEncoder(DcMotor motor, String direction) Boolean Double [] - find ou what the encoder value is
 * turnToGyroHeading(Double target heading, Double current heading) Boolean - turns Gyro to specific heading
 * driveBasedOnEncoders(Double distance, String Direction) Boolean
 * initCamera () void - initializes camera
 * key () String - figuring out wich vuMark you are sensing
 */


@Autonomous(name = "edr", group = "Other")
class EDR393Read extends OpMode {
    I2cDevice encoder;
    Servo servo;

    public void init(){
        encoder = hardwareMap.i2cDevice.get("encoder");
        servo = hardwareMap.servo.get("servo");
        // encoder.enableI2cReadMode();
    }
    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){
        servo.setPosition(1);
        if (encoder.isI2cPortReady()) {
            telemetry.addData("ready", "yeh");
            if(encoder.isI2cPortInReadMode()) {
                telemetry.addData("read", "mode");
                telemetry.addData("buffer", encoder.getCopyOfReadBuffer().toString());
                telemetry.addData("cashe", encoder.getI2cReadCache().toString());
            }
            if(encoder.isI2cPortInWriteMode()) {
                telemetry.addData("write", "mode");
            }

        }



    }
    @Override
    public void stop(){}

}

