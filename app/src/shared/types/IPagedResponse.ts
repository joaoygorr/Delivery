export interface IPagedResponse<T> {
    content: T,
    page: {
        size: number,
        number: number,
        totalElements: number,
        totalPages: number
    }
}