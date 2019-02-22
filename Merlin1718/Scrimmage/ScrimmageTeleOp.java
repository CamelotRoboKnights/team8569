package org.firstinspires.ftc.teamcode.team.Merlin1718.Scrimmage;

import android.hardware.TriggerEventListener;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.sql.Driver;

@Disabled
@TeleOp(name = "Scrimmage", group = "Scrimmage")
//@Disabled //Uncomment this if it is not wanted on the phone
public class ScrimmageTeleOp extends ScrimmageMeathods {

    public void init(){//This only runs once
        super.init();//Initializing everything needed
    }
    @Override
    public void init_loop(){//Tis runs many time during the init phase
    }
    @Override
    public void start(){}//This runs when the start button is pressed
    @Override
    public void loop(){//This runs while opmode is active
        super.drive(super.makeFieldOriented(super.joyValues(), super.orientation()));
        super.glyphWith0minimum();
        telemetry.addData("color", jewelColor());
        telemetry.update();
    }
    @Override
    public void stop(){}


}
