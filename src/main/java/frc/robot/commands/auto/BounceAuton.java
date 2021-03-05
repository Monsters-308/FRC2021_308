package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.drive.DriveAimStop;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.drive.DriveTurn;
import frc.robot.commands.hopper.ForwardHopper;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.intake.ForwardIntake;
import frc.robot.commands.shooter.ShootSpeed;
import frc.robot.subsystems.DriveSubsystem;
// import frc.robot.subsystems.HopperSubsystem;
// import frc.robot.subsystems.ShooterSubsystem;
// import frc.robot.subsystems.IndexerSubsystem;
// import frc.robot.subsystems.IntakeSubsystem;

/**
 * A complex auto command that starts the shooter drives forward, Backward, Forward intakes 2 balls Aims and shoots .
 */
public class BounceAuton extends SequentialCommandGroup {
  /**
   * Creates a new AutoNearTrench.
   *
   * @param drive The drive subsystem this command will run on
   * @param indexer The indexer subsystem this command will run on
   * @param shooter The shooter subsystem this command will run on
   * @param intake The intake subsystem this command will run on
   * @param hopper The hopper subsystem this command will run on
   */
private static final double BounceSpeed = 0.4;
private static final double BounceTurnSpeed = 0.5;
  public BounceAuton(DriveSubsystem drive) {
    addCommands(
        // new ParallelCommandGroup(
          new SequentialCommandGroup(
            new DriveDistance(                             
              28, 
              BounceSpeed,
              drive),
            new DriveTurn(                             
              -99, 
              BounceTurnSpeed * -1,
              drive),
            new DriveDistance(               
              30,
              BounceSpeed,
              drive),
            new DriveDistance(               
                75,
                BounceSpeed * -1,
                drive),
            new DriveTurn(                             
                -60, 
                BounceTurnSpeed * -1,
                drive),
            new DriveDistance(               
                35,
                BounceSpeed * -1,
                drive),
            new DriveTurn(                             
                -55, 
                BounceTurnSpeed * -1,
                drive),
            new DriveDistance(               
                  75,
                  BounceSpeed * -1,
                  drive),
            new DriveDistance(               
                  75,
                  BounceSpeed,
                  drive),
            new DriveTurn(                             
                -90, 
                BounceTurnSpeed * -1,
                drive),
            new DriveDistance(               
                30,
                BounceSpeed,
                drive),
            new DriveTurn(                             
                -85, 
                BounceTurnSpeed * -1,
                drive),
            new DriveDistance(               
                  75,
                  BounceSpeed,
                  drive),
            new DriveDistance(               
                    35,
                    BounceSpeed*-1,
                    drive),
            new DriveTurn(                             
                      -85, 
                      BounceTurnSpeed * -1,
                      drive),
            new DriveDistance(               
                    35,
                    BounceSpeed*-1,
                    drive),
            new DriveDistance(
              0,
              BounceSpeed,
              drive)
              
          )
      // )
    );
  }

}
