import axios, { AxiosInstance } from "axios";
import { authFormData } from "../types/types";

const createApiInstance = (url: string): AxiosInstance => {
    return axios.create({
        baseURL: process.env.NEXT_PUBLIC_URL + url,

        headers: {
            Accept: "aaplication/json"
        }
    })
}

export class Api {
    private api: AxiosInstance;

    constructor(url: string) {
        this.api = createApiInstance(url);
    }

    async login(user: authFormData): Promise<{ userName: string, token: string }> {
        return this.api.post("/login", user);
    }
}

export const authApi = new Api("/auth");