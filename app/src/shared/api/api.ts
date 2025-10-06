import axios, { AxiosInstance } from "axios";
import { authFormData, categoryFormData, productFormData } from "../schemas/types/types";
import { IResponse } from "../schemas/types/IResponse";
import { IPagedResponse } from "../schemas/types/IPagedResponse";

export class Api {
    private api: AxiosInstance;

    constructor(url: string) {
        this.api = axios.create({
            baseURL: process.env.NEXT_PUBLIC_URL + url,
            headers: {
                "Content-Type": "application/json",
            },
        });

        this.api.interceptors.request.use((config) => {
            const token = this.getToken();
            if (token) {
                config.headers.Authorization = `Bearer ${token}`;
            }
            return config;
        });
    }

    getToken(): string | null {
        if (typeof window === "undefined") return null;
        const cookies = document.cookie.split('; ');
        const tokenCookie = cookies.find(cookie => cookie.startsWith('token='));

        if (tokenCookie) {
            return tokenCookie.split('=')[1];
        }
        return null;
    }

    private verifyToken(token?: string): void {
        if (!token || typeof window === "undefined") return;
        document.cookie = `token=${token}; path=/; secure; samesite=strict;`;
    }

    async login(user: authFormData): Promise<{ userName: string, token: string }> {
        const res = await this.api.post("/login", user);
        this.verifyToken(res?.data?.token);
        return res?.data
    }

    async register(newUser: authFormData): Promise<{ userName: string, token: string }> {
        const res = await this.api.post("/register", newUser);
        this.verifyToken(res?.data?.token);
        return res.data;
    }

    async createProduct(product: FormData): Promise<productFormData> {
        return await this.api.post("/", product, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
        });
    }

    async createCategory(category: categoryFormData): Promise<IResponse<categoryFormData>> {
        return await this.api.post("", category);
    }

    async getCategories(): Promise<IPagedResponse<categoryFormData[]>> {
        const response = await this.api.get("");
        return response.data;
    }

    async deleteObject(id: number): Promise<void> {
        return await this.api.delete(`/${id}`);
    }

    async updateCategory(category: categoryFormData): Promise<IResponse<categoryFormData>> {
        return await this.api.put(`/${Number(category.idCategory)}`, category);
    }
}

export const authApi = new Api("/auth");

export const productApi = new Api("/product");

export const categoryApi = new Api("/category");