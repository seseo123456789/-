function result(){
    const regex = /[^0-9]/g;
    const name = document.querySelector('#name').value;
    const tel = document.querySelector('#tel').value.replace(regex,"");
    const birth = document.querySelector('#birth').value;
    const regexBirth = document.querySelector('#birth').value.replace(regex,"");
    let tell = '';
    if(tel.length == 11 && regexBirth.length == 8){
        tell = tel.slice(0,3)+'-'+tel.slice(3,7)+'-'+tel.slice(7);
        fetch('/member/findIdFetch', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
               // 데이터명 : 데이터값
                'memberName' : name,
                'memberTel' : tell,
                'memberBirth' : birth
            })
        })
        .then((response) => {
            if(!response.ok){
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return ;
            }
        
            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            if(data != null && data != ''){
                const resultId = document.querySelector('#resultId');
                resultId.innerHTML='';
                let str = '';
                str+= `<div class="row" style="margin: 26px; background-color: white; border-radius: 15px;">
                <div class="col" style="padding: 50px;">
                    회원님의 아이디는 : ${data} 입니다.
                </div>
            </div>`;
                resultId.insertAdjacentHTML("afterbegin",str);
            }
            else{
                const resultId = document.querySelector('#resultId');
                resultId.innerHTML='';
                let str = '';
                str+= `<div class="row" style="margin: 26px; background-color: white; border-radius: 15px;">
                <div class="col" style="padding: 37px; text-align: center;">
                    이름과 생년월일, 전화번호가 <br>
                    일치하지 않습니다.
                </div>
            </div>`;
                resultId.insertAdjacentHTML("afterbegin",str);
            }
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }
    else{
        if(regexBirth.length != 8 && tel.length != 11){
            alert('전화번호와 생년월일의 형식이 잘못되었습니다.')
            return;
        }
        if(regexBirth.length != 8){
            alert('생년월일의 형식이 잘못되었습니다.')
            return;
        }
        alert('전화번호 형식이 잘못되었습니다.')
    }

}