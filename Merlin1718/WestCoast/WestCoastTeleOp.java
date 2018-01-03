package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;

import android.hardware.TriggerEventListener;

import com.qualcomm.robotcore.eventloop.SyncdDevice;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.sql.Driver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
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

@TeleOp(name = "Tele", group = "Cardinal")
//@Disabled //Uncomment this if it is not wanted on the phone
public class WestCoastTeleOp extends OpMode {

    public WestCoastHardware robot = new WestCoastHardware();//The hardware map needs to be the hardware map of the robot we are using

    public void init() {//This only runs once
        robot.init(hardwareMap);

        ;//Initializing everything needed
    }

    @Override
    public void init_loop() {//Tis runs many time during the init phase
    }

    @Override
    public void start() {
    }//This runs when the start button is pressed
    // distance of variables

    @Override
    public void loop() {//This runs while opmode is active
        robot.westCoast.teleOp(gamepad1);
        //robot.glyphCollector.teleOp(gamepad2);
        robot.glyphCollector.teleOp(gamepad2);
//        robot.glyphCollector.teleRaise(gamepad2);
//        robot.glyphCollector.teleTopLayer(gamepad2);
//        robot.glyphCollector.teleBottomLayer(gamepad2);
//        if(gamepad1.b){
//            robot.jewelSorter.raise();
//        }
//        if(gamepad1.x) {
//            robot.jewelSorter.lower();//up and down are flipped
//        }
//        telemetry.addData("Blue?: ", robot.jewelSorter.isBlue());
//        telemetry.addData("Red?: ", robot.jewelSorter.isRed());
//        telemetry.addData("color", robot.jewelSorter.jewelColor());
//        telemetry.addData("height", robot.glyphCollector.currentHeight);
//        telemetry.addData("current blue: ", robot.jewelSorter.color.blue());
//        telemetry.addData("current Red: ", robot.jewelSorter.color.red());
//        telemetry.update();
    }

    @Override
    public void stop() {
    }
}