import http from "@/utils/http.ts";

// 获取文章详细
export const getArticleDetail = (id: string | string[]) => {
    return http.request({
        url: `/article/detail/${id}`,
        method: "get"
    });
}

// 获取评论
export const getComment = (type: number,typeId: number, pageNum: string, pageSize: string) => {
    return http.request({
        url: '/comment/getComment',
        method: "get",
        params: {
            type,
            typeId,
            pageNum,
            pageSize
        }
    });
}

// 用户添加评论
export const addComment = (data: object) => {
    return http.request({
        url: '/comment/auth/add/comment',
        method: "post",
        data
    });
}

// 时间轴
export const getTimeLine = () => {
    return http.request({
        url: '/article/timeLine',
        method: "get"
    });
}

// 查询不同类型下的文章列表
export function whereArticleList(type: Number, typeId: String) {
    return http.get(`/article/where/list/${typeId}`, {
        method: "get",
        params: {
            type
        }
    });
}

// 文章访问量+1
export function addArticleVisit(id: String) {
    return http.get(`/article/visit/${id}`, {
        method: "get"
    });
}
