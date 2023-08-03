function deleteFruit(id) {
    if (confirm("是否确认删除？")) {
        window.location.href = "fruit.do?id="+id+'&operate=delete';
    }
}

function page(pageNo) {
    window.location.href = "fruit.do?pageNo="+pageNo;
}