package com.example.linxj.xmlpull;

import android.app.Activity;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.linxj.Model.Book;
import com.example.linxj.Model.BookData;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.logging.LogRecord;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by linxj on 2015/9/26.
 */
public class ImportData extends Activity {
    @Bind(R.id.btn_import)
    Button btnImport;
    Handler handler = new Handler() {
        public void handleMessage(Message msg){
            if(msg.what == 0x01){
                Toast.makeText(ImportData.this,"导入成功",Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.importlayout);
        ButterKnife.bind(this);
        BookLab lab = (BookLab)this.getIntent().getSerializableExtra("Booklab");
        ArrayList<Book> books = lab.getBooks();
        final Iterator<Book> iterator = books.iterator();
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*new Thread(new Runnable() {
                    @Override
                    public void run() {*/
                String s;
                Toast.makeText(ImportData.this,"aa",Toast.LENGTH_LONG).show();
                try {
                    ActiveAndroid.beginTransaction();
                    while (iterator.hasNext()) {
                        BookData book = new BookData();
                        Book b = iterator.next();
                        book.isbn = b.getIbsn();
                        book.name = b.getName();
                        //book.index = b.getIndex();
                    }
                    ActiveAndroid.setTransactionSuccessful();

                    handler.sendEmptyMessage(0x01);

                } finally {
                    ActiveAndroid.endTransaction();
                }
               /*     }
                }).start();*/


            }
        });
    }
}
