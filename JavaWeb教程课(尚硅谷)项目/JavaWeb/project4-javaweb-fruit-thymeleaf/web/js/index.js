function deleteFruit(id) {
    if (confirm("是否确认删除？")) {
        window.location.href = "delete.do?id="+id;
    }
}