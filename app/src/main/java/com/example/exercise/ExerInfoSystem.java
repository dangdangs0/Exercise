package com.example.exercise;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

public class ExerInfoSystem extends SQLiteOpenHelper {

    ExerInfoSystem edb;
    SQLiteDatabase db;
    public ExerInfoSystem(Context context) {
        super(context,"exerDB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists exerPart(partName VARCHAR(20) PRIMARY KEY);");
        db.execSQL("CREATE TABLE if not exists exerInfo(partName VARCHAR(20), exerName VARCHAR(300) PRIMARY KEY, exerHow VARCHAR(3000) NOT NULL, exerPic VARCHAR(1000) DEFAULT NULL);");
        addInfo(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists exerPart");
//        db.execSQL("drop table if exists exerInfo");
        //onCreate(db);
    }

    public void infoUpdate(SQLiteDatabase db, String oldExerName, String newExerName, String exerHow, String exerPic){
        db.execSQL("UPDATE SET exerName="+newExerName+" AND exerHow="+exerHow+" AND exerPic="+exerPic+" WHERE exerName="+oldExerName+";");
    }
    public void infoDel(SQLiteDatabase db, String exerName){
        db.execSQL("DELETE FROM exerInfo WHERE exerName="+exerName+";");
    }
    public void addInfo(SQLiteDatabase db) {
        db.execSQL("INSERT INTO exerInfo VALUES('팔','케이블 바이셉스 컬','양다리는 어깨넓이만큼 벌리고 선다. 그립은 언더그립으로 잡고 간격은 어깨 넓이만큼 잡는다. 끌어올리면서 이두근에 최대한 집중하고, 호흡은 내뱉는다.','https://cdn5.vectorstock.com/i/thumb-large/58/64/man-doing-standing-bicep-cable-curls-exercise-vector-34705864.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('팔','바벨컬', '바벨을 들고 선 상태에서 이두근의 당기는 힘을 이용해 바벨을 들어올린다. 더이상 팔꿈치가 접혀지지 않는 한도까지 이두근에 압박을 준 뒤 천천히 바벨을 내려 준비 자세로 돌아간다.','https://image.freepik.com/free-vector/barbell-curl-demostration_1133-377.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('팔','시티드 덤벨컬', '같은 무게의 덤벨을 두손으로 각각 잡고 앉아, 숨을 내쉬며 팔꿈치를 고정시킨 상태에서 덤벨을 회전시키며 끌어당기듯 수축시킨다.','https://thumbs.dreamstime.com/b/basic-rgb-229219915.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('팔','컨센트레이션 덤벨컬','손바닥이 앞을 보도록 덤벨을 잡고 벤치에 앉아, 팔꿈치를 대퇴부 안쪽에 고정하고 숨을 내쉬면서 팔꿈치를 구부려 전완을 들어 올리고 시작 자세로 천천히 돌아오며 숨을 들이마신다.', 'https://thumbs.dreamstime.com/b/bicep-exercises-concentration-curl-flat-design-bodybuilder-character-lifting-dumbbell-140774231.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('팔','프리쳐 컬','상완이 상체 앞으로 나오는 자세를 취하여 감아올림 동작을 하여 이두근의 단두에 더 강한 자극이 가해진다.' ,'https://en.pimg.jp/023/791/920/1/23791920.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('팔','해머 컬', '두 손바닥이 마주보게 덤벨을 잡고 들어올렸다가 되는 이두 운동이다.','https://image.freepik.com/free-vector/hammer-curl-demostration_1133-371.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('팔','킥백','덤벨 등을 사용하는 운동이다. 상체를 숙인 뒤 덤벨을 잡은 팔을 뒤로 향해 뻗었다가 돌아오는 동작을 반복한다. 상체를 숙인 자세를 유지하기 힘들다면 의자 같은 받침대를 이용해도 좋다.','https://thumbs.dreamstime.com/z/booty-glutes-workout-standing-kickback-exercise-stay-home-do-sport-flat-vector-cartoon-modern-illustration-standing-212623997.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('팔','오버헤드 덤벨 프레스', '벤치에 허리를 펴고 앉아 덤벨을 잡고 귀 옆까지 덤벨을 들어준 후 어깨의 긴장을 유지한 채 팔을 위로 쭉 올려준다. 덤벨이 맞닿을 정도로 쭉 올린다.','https://thumbs.dreamstime.com/z/dumbbell-overhead-press-top-body-workout-199541702.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('팔','딥스', '평행봉을 사용하는 운동이다. 평행봉 위에서 양 팔로 평행봉을 짚은 뒤 천천해 내려갔다 올라온다. 위험한 동작이므로 전문가의 지도가 필요하다.','https://thumbs.dreamstime.com/b/man-doing-bench-tricep-dips-flat-vector-man-doing-bench-tricep-dips-flat-vector-illustration-isolated-white-background-215404254.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('팔','트라이셉스 익스텐션','덤벨 등을 사용하는 운동이다. 발을 어깨 너비로 벌리고 서서 덤벨의 한 쪽을 양손으로 잡은 뒤 손바닥이 위로 향하도록 덤벨을 올린다. 그리고 팔꿈치를 머리 옆에 고정시키고 덤벨을 천천히 머리 뒤로 내렸다가 다시 올리는 동작을 반복한다.','https://media.istockphoto.com/vectors/tricep-extension-exercise-vector-id1165642752');");
        db.execSQL("INSERT INTO exerInfo VALUES('어깨','덤벨 숄더 프레스','팔을 옆으로 벌려서 덤벨을 머리와 나란히 둔 후 머리의 중간-귀가 있는 지점에 둔다. 어깨가 솟지 않게 최대한 눌러준다.','https://thumbs.dreamstime.com/z/dumbbell-shoulder-press-vector-illustration-white-background-dumbbell-shoulder-press-exercise-strength-workout-vector-214454879.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('어깨','벤트 오버 레터럴 레이즈', '몸통을 숙인 자세로 팔을 들어올려야 하기 때문에 허리를 숙이는데 허리~등의 자세가 바르지못한, 굽은 자세가 되기 쉬우므로 복부에 힘을 주고 등을 제대로 펴 숙인다. 다리 간격은 살짝 좁게 벌리고 골반은 약간 뒤로 빼고 중심을 가운데로 잘 맞춰준다.','https://previews.123rf.com/images/lioputra/lioputra2106/lioputra210600013/169820431-woman-doing-dumbbell-bent-over-lateral-rear-delt-raises-flyes-exercise-flat-vector-illustration-isol.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('어깨','사이드 레터럴 레이즈', '어깨를 으쓱이면서 하지 않으며 올릴 때 높이는 어깨 높이 정도까지 올린다.','https://image.shutterstock.com/image-vector/man-doing-lateral-side-shoulder-260nw-1991660336.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('어깨','프론트 레터럴 레이즈', '팔을 곧게 펴서 팔꿈치를 고정시킨 상태로 호흡을 내쉬며 어깨의 힘만으로 덤벨을 눈높이까지 올린 후 호흡을 내쉬며 허벅지에 닿기 직전까지 내린다.','https://i.pinimg.com/736x/75/21/c3/7521c3f57a75bebde1c4ebb8645ef2d6--set-of-page-.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('어깨','케이블 레터럴 레이즈', '팔꿈치를 가볍게 굽혀 핸들을 잡은쪽 팔을 측면으로 들어올린다. 이때 팔꿈치는 고정하고 측면 삼각근에 집중한다.','https://cdn.shopify.com/s/files/1/0269/5551/3900/files/Cable-One-Arm-Lateral-Raise_3e57189f-cdf3-46ee-9a89-6ca054eae56a_600x600.png?v=1612138775');");
        db.execSQL("INSERT INTO exerInfo VALUES('어깨','오버 헤드 바벨 프레스', '팔꿈치가 완전히 펴질 때까지 바를 머리 위로 밀어 올렸다가 천천히 처음 자세로 돌아온다.','https://cdn.shopify.com/s/files/1/0269/5551/3900/files/Seated-Barbell-Shoulder-Press_600x600.png?v=1619977796');");
        db.execSQL("INSERT INTO exerInfo VALUES('하체','스쿼트', '발 끝을 11자로 맞춘 후 천천히 몸을 낮춰 앉은 자세를 취했다가 다시 올라온다. 이때 무릎은 발끝과 일치해야 하며 무릎 각도가 90도가 될 때까지 내려간다. 또한 허리 역시 곧게 편 상태를 유지하도록 한다.','https://metro.co.uk/wp-content/uploads/2021/10/GettyImages-1284067703.jpg?quality=90&strip=all');");
        db.execSQL("INSERT INTO exerInfo VALUES('하체','데드리프트', '등이 평평해지고 척추가 중립을 이루게 된다. 여기서 팔이 펴지게 되어 단단하게 들어올릴 수 있는 자세가 된다. 또한 바벨의 무게감이 약간 느껴지는 상태가 된다. 여기서 무릎을 살짝 바깥쪽으로 벌려준다.','https://image.shutterstock.com/image-vector/man-doing-barbell-deadlifts-exercise-260nw-2031951569.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('하체','레그컬','패드 끝 부분을 무릎의 뼈 바로 윗쪽에 오도록 맞춰서 눕고, 밑의 걸이 기구에 아킬레스건 윗쪽이 닿도록 다리를 걸고 손잡이를 잡는다.', 'https://thumbs.dreamstime.com/b/basic-rgb-229305874.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('하체','덤벨런지', '가슴을 내밀면서 한쪽 다리로 크게 한 발짝 앞으로 내디딘다. 이때 뒤에 남아 있는 다리의 무릎은 바닥 쪽으로 낮추고, 앞쪽 다리의 정강이는 최대한 수직 상태를 유지하도록 한다. 앞쪽 발에 힘을 주면서 몸을 뒤로 밀어 시작 자세로 돌아온다.','https://thumbs.dreamstime.com/z/man-doing-dumbbell-lunges-vector-set-workout-icons-flat-style-isolated-white-background-man-doing-dumbbell-lunges-vector-198695566.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('하체','바벨런지', '양손을 바벨을 잡아 어깨 뒤쪽 승모근에 걸치고 허리를 펴고 선 후, 몸을 낮추어 내민 다리의 무릎이 90도 각도가 되도록 하며, 무릎이 앞꿈치보다 앞으로 나오면 안된다.','https://image.shutterstock.com/image-vector/sport-bodybuilding-lifestyle-people-concept-260nw-1831069621.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('하체','프론트 스쿼트', '최대한 높이 세워서 팔 부분이 일자가 되도록, 바닥과 수평을 이루도록 하며 다리는 보통 스쿼트보다 더 넓게 벌려서 하체를 깊게 숙이는 풀 스쿼트 자세로 한다.','https://thumbs.dreamstime.com/z/crossfit-workout-training-open-games-championship-sport-man-training-olympic-heavy-weight-front-squat-exercise-gym-164443717.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('하체','레그 익스텐션','엉덩이와 등을 떼지 않고 공차듯이 발끝을 뻥 차지 않는다. 근육은 끝까지 수축시키되 뼈는 끝까지 펴지 말고, 이완시킬때에도 긴장을 놔버릴때까지 완전히 펴지 않는다.', 'https://thumbs.dreamstime.com/b/leg-extension-simulator-sport-exercises-gym-illustration-active-lifestyle-vector-116989255.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('가슴','벤치프레스', '꿈치를 완전히 편다면 횟수 사이에 잠깐씩 휴식이 이뤄지기 때문에 고중량 세트에 좋으며, 전면 삼각근과 삼두근의 개입이 많다.','https://st2.depositphotos.com/5777248/10339/v/950/depositphotos_103399942-stock-illustration-man-exercising-on-bench-press.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('가슴','인클라인 벤치프레스', '바벨을 가슴에 가까이 내렸을때 가슴이 쭈우욱 펴지는것처럼 최대한 당겨줬다가 다시 밀어내는것이다.','https://previews.123rf.com/images/lioputra/lioputra2011/lioputra201100085/158979432-incline-barbell-bench-press-exercise-flat-vector-illustration-isolated-on-white-background-workout-c.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('가슴','덤벨 플라이', '평평한 플랫 벤치에 누워 몸 위쪽으로 날개짓 하듯이 반원을 그리며 가슴을 모아준다.','https://cdn.shopify.com/s/files/1/0269/5551/3900/files/Incline-Dumbbell-Fly_44d253c3-da60-45b2-b0ba-88a3bb79da09_600x600.png?v=1612137870');");
        db.execSQL("INSERT INTO exerInfo VALUES('가슴','팩덱 플라이','의자와 등의 윗부분과 엉덩이 부분을 의자에 붙이고 가슴이 이완이 되도록 하고, 팔을 가슴 안쪽으로 모아준다.', 'https://thumbs.dreamstime.com/z/art-illustration-200146493.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('가슴','덤벨 프레스','덤벨은 가로로 놓이게 잡아주고 팔을 굽히면서 덤벨을 가슴 깊이 끌어 당겨 줍니다. 가슴이 확실하게 스트레칭 하는것처럼 펴지는 느낌이 날때까지 덤벨을 당겨준다.', 'https://previews.123rf.com/images/lioputra/lioputra2010/lioputra201000172/157886722-man-doing-dumbbell-flat-bench-press-chest-exercise-flat-vector-illustration.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('가슴','풀오버', '벤치에 누운 상태에서 바벨을 어깨 넓이로 잡고 들어올린다. 이때, 어깨가 다치지 않게하고 팔과 어깨에 최대한 힘을 빼기 위하여 팔을 살짝 굽혀서 아치 형태로 만든다.팔이 과도하게 굽혀지지 않도록 하면서 바벨을 머리 뒤로 내린다.','https://thumbs.dreamstime.com/b/man-doing-dumbbell-pullover-exercise-flat-vector-man-doing-dumbbell-pullover-exercise-flat-vector-illustration-isolated-white-223602955.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('등','풀업','팔을 굽혀 몸통을 위로 올리는데요. 말했듯이 턱을 거는 자세가 아니라 가슴을 들어서 봉과 가까이 하는 자세로 운동이다.','https://image.shutterstock.com/image-vector/man-doing-pullups-workout-fitness-260nw-1383773363.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('등','랫풀 다운', '턱걸이를 하듯 어깨보다 좀 더 넓게 바를 잡은 다음, 상체를 뒤로 젖히고 팔이 아니라 등의 힘으로 당겨 내려오는 방식이다.','https://thumbs.dreamstime.com/b/wide-grip-lat-pull-down-exercise-sport-exercises-gym-workout-illustration-active-lifestyle-vector-113673541.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('등','벤트 오버 바벨로우', '상체를 숙여서 바벨로 노를 젓는 동작을 하는 운동이다.','https://thumbs.dreamstime.com/z/athlete-performs-bent-over-rows-exercise-barbell-minimalistic-line-style-gym-character-set-athlete-performs-198695546.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('등','티바 로우', '무릎을 약간 굽혀 하복부를 지지대에 고정시켜 손바닥이 서로 마주보도록 바를 잡은 후 손잡이를 들어올려 광배근의 힘을 이용하여 등을 활처럼 젖히도록 한다.','https://image.shutterstock.com/image-vector/bentover-row-top-body-workout-260nw-1837676119.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('등','덤벨 로우', '어깨보다 넓은 간격으로 팔을 벌려 덤벨을 잡고, 양 발은 어깨 너비 간격만큼 벌려 선다. 무릎은 굽히고 엉덩이는 뒤로 빼면서 상체를 앞으로 숙여주는데, 각도는 무릎과 약 45도 이상으로 넘어가지 않도록 한다.','https://thumbs.dreamstime.com/z/woman-doing-dumbbell-row-exercise-flat-vector-illustration-isolated-white-background-221211959.jpg');");
        db.execSQL("INSERT INTO exerInfo VALUES('등','케이블 로우','핸들에서 적당한 거리를 두고 앉은 뒤, 허리가 곧게 편 상태를 계속 유지해 주며 핸들을 몸 쪽으로 당겼다 놓는 동작을 반복한다.', 'https://media.istockphoto.com/vectors/young-woman-in-sportswear-doing-seated-cable-row-girl-doing-sports-in-vector-id1251269714?s=612x612');");
        db.execSQL("INSERT INTO exerInfo VALUES('등','오버헤드 랫풀 다운','두 팔로 웨이트를 내리고 한 팔로 웨이트를 올립니다.', 'https://thumbs.dreamstime.com/z/basic-rgb-215192591.jpg');");

    }
    public void addPart(SQLiteDatabase db, String partName){
        db.execSQL("INSERT INTO exerPart VALUES('팔')");
        db.execSQL("INSERT INTO exerPart VALUES('어깨')");
        db.execSQL("INSERT INTO exerPart VALUES('하체')");
        db.execSQL("INSERT INTO exerPart VALUES('가슴')");
        db.execSQL("INSERT INTO exerPart VALUES('등')");
        }



}
