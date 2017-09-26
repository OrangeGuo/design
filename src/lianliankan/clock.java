package lianliankan;
import javax.swing.JLabel;

class clock  implements Runnable {
    public int time_total;
    JLabel jLabel=null;
    public clock(int time,JLabel jLabel)
    {
    	this.time_total=time;
    	this.jLabel=jLabel;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(time_total>=0)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			this.jLabel.setText("ʣ��ʱ��:"+String.valueOf(this.time_total));
			this.time_total--;
		}
	}

}
