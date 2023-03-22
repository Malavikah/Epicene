import os
from flask import *
from werkzeug.utils import secure_filename

from src.database import *

app=Flask(__name__)

@app.route('/login',methods=['post'])
def login():

    username=request.form['uname']
    password=request.form['pass']
    qry="select*from `login` where USERNAME=%s and `PASSWORD`=%s and TYPE='user'"
    val=(username,password)
    s=selectone(qry,val)
    print(s)

    if s is None:
        return jsonify({'task':'invalid'})
    else:
        id=s[0]
        return jsonify({'task':'valid',"id" : id })

@app.route('/reg',methods=['post'])
def reg():
    print(request.form)

    uname=request.form['name']
    uplace=request.form['place']
    upost=request.form['post']
    upin=request.form['pin']
    uphno = request.form['phno']
    uemail = request.form['email']
    username=request.form['uname']
    password=request.form['pass']
    ds=request.form['ds']

    qry="insert into login values(null,%s,%s,'pending')"
    val = (username, password)
    id=iud(qry, val)
    req = str(id) + "." + "jpg"
    print(req)
    file = request.files['files']
    ff = secure_filename(file.filename)
    print(ff)
    fl = ff.split('.')
    print(fl[1])
    file.save(os.path.join('./static/upload', req))
    qry="INSERT INTO`transgender`VALUES(null,%s,%s,%s,%s,%s,%s,%s,%s)"
    val1=(str(id),uname,uplace,upost,upin,ds,uemail,uphno)
    iud(qry,val1)
    return jsonify({'task': 'success'})

@app.route('/viewedu',methods=['post'])
def viewedu():
    qry="SELECT * FROM `educationsupport`"
    res=androidselectallnew(qry)
    return jsonify(res)


@app.route('/viewjob',methods=['GET','post'])
def viewjob():
    id = request.form['j_type']
    qry ="SELECT * from jobsupport where jobtype=%s"
    res = androidselectall(qry, id)
    return jsonify(res)




@app.route('/viewhealth',methods=['GET','post'])
def viewhealth():
    id = request.form['h_type']
    print(id)
    qry = "SELECT * FROM `healthtip` WHERE `jobtype`=%s"
    res = androidselectall(qry, id)
    print(res)
    return jsonify(res)






@app.route('/viewcoun',methods=['GET','post'])
def viewcoun():

    qry = "SELECT * FROM `counselling`"
    res = androidselectallnew(qry)
    return jsonify(res)



@app.route('/viewlegal',methods=['GET','post'])
def viewlegal():
    qry="SELECT * FROM `legalassistant`"
    res=androidselectallnew(qry)
    return jsonify(res)

@app.route('/in_message2',methods=['post'])
def in_message():
    print(request.form)
    fromid = request.form['fid']
    print("fromid",fromid)

    toid = request.form['toid']
    print("toid",toid)

    message=request.form['msg']
    print("msg",message)
    qry = "INSERT INTO `chat` VALUES(NULL,%s,%s,%s,CURDATE())"
    value = (fromid, toid, message)
    print("pppppppppppppppppp")
    print(value)
    iud(qry, value)
    return jsonify(status='send')

@app.route('/view_message2',methods=['post'])
def view_message2():
    print("wwwwwwwwwwwwwwww")
    print(request.form)
    fromid=request.form['fid']
    print(fromid)
    toid=request.form['toid']
    print(toid)
    lmid = request.form['lastmsgid']
    print("msgggggggggggggggggggggg"+lmid)
    sen_res = []
    # qry="SELECT * FROM chat WHERE (fromid=%s AND toid=%s) OR (fromid=%s AND toid=%s) ORDER BY DATE ASC"
    qry="SELECT `from_id`,`message`,`date`,`chat_id` FROM `chat` WHERE `chat_id`>%s AND ((`to_id`=%s AND  `from_id`=%s) OR (`to_id`=%s AND `from_id`=%s)  )  ORDER BY chat_id ASC"

    val=(str(lmid),str(toid),str(fromid),str(fromid),str(toid))
    print("fffffffffffff",val)
    res = androidselectall(qry,val)
    print("resullllllllllll")
    print(res)
    if res is not None:
        return jsonify(status='ok', res1=res)
    else:
        return jsonify(status='not found')





@app.route('/viewlegals',methods=['post'])
def viewlegals():
    qry="SELECT * FROM `legal_assistant`"
    res=androidselectallnew(qry)
    return jsonify(res)





@app.route('/viewschedule',methods=['GET','post'])
def viewschedule():
    id=request.form['leid']
    qry ="SELECT * FROM `schedule` where legal_id=%s"
    res = androidselectall(qry,id)
    print(res)
    return jsonify(res)




@app.route('/viewhuman',methods=['post'])
def viewhuman():
    qry="SELECT * FROM `humanrights`"
    res=androidselectallnew(qry)
    return jsonify(res)





@app.route('/apmtlegal',methods=['GET','post'])
def apmtlegal():

    qry = "SELECT  * FROM `legalassistant`"
    res = androidselectallnew(qry)
    return jsonify(res)



@app.route('/apmtrqst',methods=['post'])
def apmtrqst():
    uid=request.form['u_id']
    lid = request.form['s_id']
    qry="INSERT INTO `booking` VALUES(NULL,%s,%s,CURDATE(),'pending')"
    val=(uid,lid)
    iud(qry,val)
    return jsonify({'task':'success'})




@app.route('/viewapmt',methods=['GET','post'])
def viewapmt():
    id = request.form['id']

    qry = "SELECT `booking`.*,`legalassistant`.`NAME` FROM `booking` JOIN `schedule` ON `schedule`.`schedule_id`=`booking`.`schedule_id` JOIN `legalassistant` ON `legalassistant`.`LOGIN_ID`=`schedule`.`legal_id` WHERE `booking`.`status`='booked' AND `booking`.`from_id`=%s"
    res = androidselectall(qry, id)
    return jsonify(res)

@app.route('/viewevent',methods=['GET','post'])
def viewevent():
    qry = "SELECT * FROM`events`"
    res = androidselectallnew(qry)
    print(res)
    return jsonify (res)



@app.route('/uhelp',methods=['post'])
def uhelp():
    lid=request.form['lid']
    latti=request.form['latitude']
    longi=request.form['longitude']
    print(latti, longi)
    qry="insert into emergency_help values(NULL,curdate(),%s,%s,%s)"
    val=(lid,'12.0320','75.7390')
    iud(qry,val)
    return jsonify({'task':'success'})


@app.route("/and_view_awareness", methods=['post'])
def and_view_awareness():
    q = (
    "select awarness.*,localcordinator.name  from awarness, localcordinator where localcordinator.LID=awarness.location_id")
    res = androidselectallnew(q)
    return jsonify(res)

@app.route("/and_view_medical", methods=['post'])
def and_view_medical():
    q = ("select * from medicalofficer")
    res = androidselectallnew(q)
    return jsonify(res)

@app.route("/and_view_meeting", methods=['post'])
def and_view_meeting():
    q = (
    "select notification.*, legalassistant.NAME from legalassistant, notification where notification.legal_id=legalassistant.LOGIN_ID")
    res = androidselectallnew(q)
    print(res)
    return jsonify(res)

if __name__ =="__main__":
    app.run(host="0.0.0.0",port=5000)    