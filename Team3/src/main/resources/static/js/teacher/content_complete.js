function look(thisDetail,content){
    const CCB = document.getElementById('CCB');
    const CCHB = document.getElementById('CCHB');
    const CCCB = document.getElementById('CCCB');
    
    if(CCB.style.display == 'none') {
        CCB.style.display = 'inline-block';
        CCHB.style.display = 'none';
        CCCB.style.display = 'none';

	}
    const className = thisDetail.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.textContent;
    const stuName = thisDetail.parentElement.previousElementSibling.previousElementSibling.textContent;
    const cstDate = thisDetail.parentElement.previousElementSibling.textContent;
    const cstNum =  thisDetail.previousElementSibling.value;
    const plusHere = document.querySelector(".here");
    let str = '';
    plusHere.innerHTML = '';
    str+=`<table class="table table-striped table-bordered trLine" id="detail" style="text-align: center; vertical-align: middle;">
            <colgroup>
                <col width="40%">
                <col width="25%">
                <col width="35%">
            </colgroup>
            <thead>
                <tr>
                    <td><strong>소속</strong></td>
                    <td><strong>이름</strong></td>
                    <td><strong>상담일</strong></td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>`;
                    str+=className;
                    str+=`</td>
                    <td>`
                    str+=stuName;
                    str+=`</td>
                    <td>`
                    str+=cstDate;
                    str+=`</td>
                </tr>
                <tr>
                    <td colspan="3" style="font-weight: bolder;">
                        상담내용
                    <input type="hidden" id="consultNum" value="`;
                    str+= cstNum
                    str+=`">
                    <input type="hidden" id="content" value="`;
                    str+= content;
                    str+=`">
                    </td>
                </tr>
                <tr>
                    <td colspan="3" id="cctd" style="height: 270px; vertical-align: top;">
                        `;
                        str+=content
                        str+=`
                    </td>
                </tr>
            </tbody>
        </table>
        `
        
        ;
    plusHere.insertAdjacentHTML("afterbegin",str)
}

function crystalConsultContent(){
    const selected_td = document.querySelector("#cctd");
    const consultNum = document.getElementById('consultNum').value;
    const CCB = document.getElementById('CCB');
    const CCHB = document.getElementById('CCHB');
    const CCCB = document.getElementById('CCCB');
    if(CCB.style.display !== 'none') {
	    CCB.style.display = 'none';
	}
    if(CCHB.style.display == 'none') {
	    CCHB.style.display = 'inline-block';
	    CCCB.style.display = 'inline-block';
	}
    selected_td.innerHTML="";
    let str =``;
    str+=`<form action="/consult/addConsultContent" method="post" id="newContentCrystal">
                <input type="hidden" name="consultNum" value="`
                str+=consultNum;
                str+=`">
                <textarea class="form-control" name="consultContent" rows="10"></textarea>
            </form>`;
    selected_td.insertAdjacentHTML("afterbegin",str)
}



function crystalContentOk(){
    const sbm = document.querySelector('#newContentCrystal');
    sbm.submit();
}

function crstalCancle(){
    const goBack = document.querySelector("#content").value;
    const selected_td = document.querySelector("#cctd");
    selected_td.innerHTML="";
    let str =``;
    str+=goBack;
    selected_td.insertAdjacentHTML("afterbegin",str);
    const CCB = document.getElementById('CCB');
    const CCHB = document.getElementById('CCHB');
    const CCCB = document.getElementById('CCCB');
    if(CCB.style.display == 'none') {
	    CCB.style.display = 'inline-block';
	}
    if(CCHB.style.display !== 'none') {
	    CCHB.style.display = 'none';
	    CCCB.style.display = 'none';
	}
}



