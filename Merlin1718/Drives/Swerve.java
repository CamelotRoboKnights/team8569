package org.firstinspires.ftc.teamcode.team.Merlin1718.Drives;

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



class Swerve extends OpMode {

    public class SingleModule {
        DcMotor motor;
        Servo servo;
        I2cDevice encoder;
        double ticksPerRotation = 392;
        double currentAngle = ticksPerRotation*360;//calculate current angle
        double wheelDirection = 1;


        SingleModule (DcMotor motor, Servo servo, I2cDevice encoder){
            this.motor = motor;
            this.servo = servo;
            this.encoder = encoder;
        }
        void drive(double targetAngle, double speed){
            turnAssemblyToAngle(speed);
            this.motor.setPower(Range.clip(speed, -1, 1) * wheelDirection);
        }
        void turnAssemblyToAngle(double targetAngle){//needs work
            double angleDifference = targetAngle - currentAngle;//if counterclockwise is positive
            double servoSpeed = 0;

            if(angleDifference > 180) {//if the angle is greater than 180 make it the opposite angle and flip motor power
                angleDifference -= 360;
                this.wheelDirection = wheelDirection * -1;
            }
            else if(angleDifference < -180) {//if the angle is less than 180 make it the opposite
                angleDifference += 360;
                this.wheelDirection = wheelDirection * -1;
            }


            this.servo.setPosition(Range.clip(servoSpeed, 0, 1));
        }


    }
    public class SwerveDrive {
        SingleModule frontLeft;
        SingleModule frontRight;
        SingleModule backRight;
        SingleModule backLeft;
        SwerveDrive (SingleModule frontLeft, SingleModule frontRight, SingleModule backRight, SingleModule backLeft){
            this.frontLeft = frontLeft;
            this.frontRight = frontRight;
            this.backRight = backRight;
            this.backLeft = backLeft;
        }
        void drive (double targetAngle, double speed) {
            this.frontLeft.drive(targetAngle, speed);
            this.frontRight.drive(targetAngle, speed);
            this.backRight.drive(targetAngle, speed);
            this.backLeft.drive(targetAngle, speed);
        }
        void turn (double speed) {
            this.frontLeft.drive(-45, speed);
            this.frontRight.drive(45, speed);
            this.backRight.drive(-45, speed);
            this.backRight.drive(45, speed);
        }
    }
    public void init(){}
    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){}
    @Override
    public void stop(){}

}

