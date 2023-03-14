package com.example.form;

import javax.validation.GroupSequence;


//GroupSrquenceでバリデーションの順番を設定
@GroupSequence({ValidGroup1.class,ValidGroup2.class})
public interface GroupOrder {

}
