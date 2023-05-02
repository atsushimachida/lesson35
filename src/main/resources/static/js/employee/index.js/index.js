/** 削除ボタンを押した時の処理 */
function clickBtnDelete() {
    var idck;

    // チェックした要素を取得する
    if (typeof document.frm.idck.length === 'undefined') {
        idck = [{ 'checked': document.frm.idck.checked }];
    } else {
        idck = document.frm.idck;
    }

    // 確認ダイアログを表示
    if (window.confirm(`削除して良いですか？`)) {
        // OKが押されたら処理を実行
        return true;
    } else {
        return false;
    }
}

// 削除ボタンに関数を割り当てる
document.getElementById("deleteRun").onclick = clickBtnDelete;