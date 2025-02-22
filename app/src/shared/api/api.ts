import axios, { AxiosInstance } from "axios";
import { authFormData } from "../types/types";

export class Api {
    private api: AxiosInstance;

    constructor(url: string) {
        this.api = axios.create({
            baseURL: process.env.NEXT_PUBLIC_URL + url,
            headers: { Accept: "application/json" }
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
        return localStorage?.getItem("token") ?? null;
    }
    private verifyToken(token?: string): void {
        if (!token || typeof window === "undefined") return;
        localStorage.setItem("token", token);
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
}

export const authApi = new Api("/auth");