package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.subsystems.IndexerSubsystem;

/**
 * A simple command that runs the indexer forward and manages balls with the {@link IndexerSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AutoIndexer extends CommandBase {
  // The subsystem the command runs on
    public enum IndexerStage {
        EMPTY,
        BALL_PRESENT,
        BALL_INDEXED,
        BALL_AT_SHOOT,
        FULL
    }

  private final IndexerSubsystem m_indexerSubsystem;
    public boolean m_autoLoad = false;
    public boolean m_autoShoot = false;
    public boolean m_complete = false;
    public IndexerStage m_loadingStage = IndexerStage.EMPTY;
    public DigitalInput m_sensorShoot;
    public DigitalInput m_sensorBallReady;
    public DigitalInput m_sensorBallIndexed;

  public AutoIndexer(IndexerSubsystem subsystem, boolean autoLoad, boolean autoShoot) {
    m_indexerSubsystem = subsystem;
    m_autoLoad = autoLoad;
    m_autoShoot = autoShoot;
    m_sensorShoot = new DigitalInput(Constants.IndexerConstants.kShootSensorInput);
    m_sensorBallReady = new DigitalInput(Constants.IndexerConstants.kBallReadySensorInput);
    m_sensorBallIndexed = new DigitalInput(Constants.IndexerConstants.kBallIndexedSensorInput);

    if(m_autoShoot){ // if requested to shoot it is higher priority
        m_autoLoad = false;
    }
    addRequirements(m_indexerSubsystem);
  }

  @Override
  public void initialize() {
    if(!m_sensorBallIndexed.get() && m_sensorBallReady.get()){ // handle specil case where they could start the auto index only with one ball at the Indexed state.
        // no we see the ball has indexed but no ball is staged
        m_loadingStage = IndexerStage.BALL_INDEXED;
        //send to the next stage and wait for ball present
        m_indexerSubsystem.stopIndexer();
    }else{
        m_loadingStage = IndexerStage.EMPTY;
    }
    m_complete = false;
  }

  @Override
  public void execute() {
      // here is where we want to auto index the balls if we have one present or stagged

      if(m_autoLoad){ // if m_autoLoad keep loading until full
        if(!m_sensorShoot.get()){
            m_loadingStage = IndexerStage.BALL_AT_SHOOT;
            m_indexerSubsystem.stopIndexer();
        }

        SmartDashboard.putBoolean("Ball Ready", m_sensorBallReady.get());
        SmartDashboard.putBoolean("Ball Indexed", m_sensorBallIndexed.get());
        SmartDashboard.putBoolean("Shoot", m_sensorShoot.get());

        // SmartDashboard.putNumber("State",m_loadingStage);
        switch(m_loadingStage){
            case EMPTY:  // if we are EMPTY and auto load then run the indexer until ball ready sensor
                if(!m_sensorBallReady.get()){
                    // now we see a ball is ready changed states
                    m_loadingStage = IndexerStage.BALL_PRESENT;
                }
                m_indexerSubsystem.forwardIndexer();
                SmartDashboard.putNumber("State",0);
                break;
            case BALL_PRESENT:
                if(!m_sensorBallIndexed.get() && m_sensorBallReady.get()){
                    // no we see the ball has indexed but no ball is staged
                    m_loadingStage = IndexerStage.BALL_INDEXED;
                    //send to the next stage and wait for ball present
                    m_indexerSubsystem.stopIndexer();
                }else{
                    m_indexerSubsystem.forwardIndexer();
                }
                SmartDashboard.putNumber("State",1);
                break;
            case BALL_INDEXED:
                if(!m_sensorBallReady.get()){
                    // if we see that ball is at ready stage then set the stage back to Ball Present
                    m_loadingStage = IndexerStage.BALL_PRESENT;
                }
                SmartDashboard.putNumber("State",2);
                break;
            case BALL_AT_SHOOT:
                m_indexerSubsystem.stopIndexer();
               m_complete = true;
                SmartDashboard.putNumber("State",3);
                break;
            case FULL:
                SmartDashboard.putNumber("State",4);
            break;
            
        }
      }else if(m_autoShoot){// auto shoot is a little bit different as we want to keep indexing until no balls are through the system

      }
  }

  @Override
  public boolean isFinished() {
      if(m_complete){
          return true;
      }else{
          return false;
      }
  }
}