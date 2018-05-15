package wby.laowang.greendaoxmt;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import wby.laowang.greendaoxmt.gen.DUserDao;
import wby.laowang.greendaoxmt.gen.DaoMaster;
import wby.laowang.greendaoxmt.gen.DaoSession;
import wby.laowang.greendaoxmt.recycler.MyAdapter;

public class MainActivity extends AppCompatActivity {

    private EditText ed_name;
    private EditText ed_age;
    private RecyclerView dao_rec;
    private DUserDao dUserDao;
    private String name;
    private String age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        DaoUse();
    }

    public void initViews(){

        ed_name = findViewById(R.id.ed_name);
        ed_age = findViewById(R.id.ed_age);
        dao_rec = findViewById(R.id.dao_rec);
    }


    public void DaoUse(){
        //创建数据库
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "User.db", null);
        //得到数据库连接对象
        SQLiteDatabase database = openHelper.getWritableDatabase();
        //得到数据库管理者
        DaoMaster daoMaster = new DaoMaster(database);
        //得到daoSession，可以执行增删改查操作
        DaoSession daoSession = daoMaster.newSession();

        dUserDao = daoSession.getDUserDao();


    }

    public void daoselect(View view) {
        List<DUser> list = dUserDao.queryBuilder().list();
        dao_rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        MyAdapter myAdapter = new MyAdapter(list,this);
        dao_rec.setAdapter(myAdapter);

    }

    public void daoadd(View view) {

        name = ed_name.getText().toString();
        age = ed_age.getText().toString();
        dUserDao.insert(new DUser(null, name, age));
        daoselect(view);
    }

    public void daoupdate(View view) {

        DUser dUser = dUserDao.queryBuilder().where(DUserDao.Properties.Name.eq(name)).build().unique();
        if (dUser != null){
            String sname = ed_name.getText().toString();
            String sage = ed_age.getText().toString();
            dUser.setName(sname);
            dUser.setAge(sage);
        }
    }
    public void daodelete(View view) {
        dUserDao.deleteAll();
    }
}
