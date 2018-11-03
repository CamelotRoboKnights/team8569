package org.firstinspires.ftc.teamcode.team.K9Robo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.team.Merlin1617.Merlin3.Merlin3Hardware;

// Start out by writing a arcade joystick drive for the K9 - commands you should need are below
// Once your done with that try to work with the servos
// Look at other codes to be able to do that and make sure you comment your code
// remember ;
// I am still reachable so reach out to me if you are stuck
// When you are done try it on the robot

@TeleOp(name="ZigyT", group="ZigyT")

public class ZigyT extends OpMode {

    HardwareK9Robo robot = new HardwareK9Robo();//The hardware map needs to be the hardware map of the robot we are using

    public void init(){
        robot.init(hardwareMap);
    }
    double neck = 0;
    double mouth = 0;
    // upon int set mouth and neck to 0

    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){
        // Write your code in here