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


@Autonomous(name = "RedC", group = "Scrimmage")
//@Disabled //Uncomment this if it is not wanted on the phone
public class WestCoastRedC extends OpMode {

    public WestCoastHardware robot = new WestCoastHardware();//The hardware map needs to be the hardware map of the robot we are using

    public void init(){//This only runs once
        robot.init(hardwareMap);

        ;//Initializing everything needed
    }
    @Override
    public void init_loop(){//Tis runs many time during the init phase
    }
    @Override
    public void start(){}//This runs when the start button is pressed

    private String color = "red";
    private double driveForwardToKnockDistance;
    private double driveBackToKnockDistance;
    private double spinRightToKnockOffRightJewel = -7;
    private double spinLeftToKnockOffLeftJewel  = 7;
    private double driveDistanceToRightColumn = 24;//
    private double driveDistanceToCenterColumn = 30;// 30
    private double driveDistanceToLeftColumn = 36;//36
    private double driveForwardToCryptobox = 10;
    private double driveAwayFromCryptobox = 5;//2



    private String currentCase = "DropSorter";
    private String jewel = "null";
    private String column = "null";
    @Override
    public void loop(){//This runs while opmode is active
        telemetry.addData("CurrentCase", currentCase);
        telemetry.addData("jewel", jewel);
        telemetry.addData("colum", column);
        switch (currentCase){
            case "DropSorter":
                boolean doneYet;
                doneYet = true;//sorter("down");
                // glyphAuto("close");
                if(doneYet){
                    currentCase = "IDCryptographPicAndJewelColor";
                }
                break;
            case "IDCryptographPicAndJewelColor":
                jewel = "blue";//super.jewelColor();
                column = "RIGHT";//super.key();
                if(!jewel.equals("null") && !column.equals("null")){
                    if(jewel.equals(color)){
                        currentCase = "SpinRightToKnockOffRightJewel";
                    }
                    else {
                        currentCase = "SpinLeftToKnockOffLeftJewel";
                    }
                }
                break;
            case "SpinRightToKnockOffRightJewel":
                doneYet = robot.westCoast.turnToGyroHeading(spinRightToKnockOffRightJewel,0); //TurnToGyroHeading(spinRightToKnockOffRightJewel, 0);
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            case "SpinLeftToKnockOffLeftJewel":
                doneYet = robot.westCoast.turnToGyroHeading(spinLeftToKnockOffLeftJewel, 0);
                if(doneYet){
                    currentCase = "RaiseSorter";
                }
                break;
            case "RaiseSorter":
                doneYet = true; //sorter("up");
                if(doneYet){
                    currentCase = "SpinBackToStartingPosition";
                }
                break;
            case "SpinBackToStartingPosition":
                doneYet = robot.westCoast.turnToGyroHeading(0, 0);
                if(doneYet){
                    currentCase = choseColumnCase(column);
                }
                break;
            case "ToRightColumn":
                doneYet = robot.westCoast.driveBasedOnEncoders(driveDistanceToRightColumn,1);
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;
            case "ToCenterColumn":
                doneYet = robot.westCoast.driveBasedOnEncoders(driveDistanceToCenterColumn,1);
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;
            case "ToLeftColumn":
                doneYet = robot.westCoast.driveBasedOnEncoders(driveDistanceToLeftColumn,1);
                if(doneYet){
                    currentCase = "SpinTo90";
                }
                break;
            case "SpinTo90":
                doneYet = robot.westCoast.turnToGyroHeading(-90,0);
                if(doneYet){
                    currentCase = "DriveForward";
                }
                break;
            case "DriveForward":
                doneYet = robot.westCoast.driveBasedOnEncoders(driveForwardToCryptobox,1);
                if(doneYet){
                    currentCase = "ReleaseGripper";
                }
                break;
            case "ReleaseGripper":
                doneYet = true; //glyphAuto("open");
                if(doneYet){
                    currentCase = "DriveForward2";
                }
                break;
            case "DriveForward2":
                doneYet = robot.westCoast.driveBasedOnEncoders(2,1);
                if(doneYet){
                    currentCase = "DriveBack";
                }
                break;
            case "DriveBack":
                doneYet = robot.westCoast.driveBasedOnEncoders(driveAwayFromCryptobox,-1);
                if(doneYet){
                    currentCase = "End";
                }
                break;
            case "End":
                robot.westCoast.drive(0,0);
                break;
            default:
                robot.westCoast.drive(0,0);
                telemetry.addData("What Are You", " Doing");
                telemetry.update();
        }
    }
    @Override
    public void stop(){}








    private String choseColumnCase(String column){
        switch (column){
            case "RIGHT":
                return "ToRightColumn";
            case "CENTER":
                return "ToCenterColumn";
            case "LEFT":
                return "ToLeftColumn";
            default:
                telemetry.addData("Not Left Right Or", " Center");
                return "";
        }
    }

}
