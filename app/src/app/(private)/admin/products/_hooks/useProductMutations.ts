import { useMutation, useQueryClient } from "@tanstack/react-query"
import { ProductFormData } from "@/shared/types/types";
import { productApi } from "@/shared/api/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";
import { IPagedResponse } from "@/shared/types/IPagedResponse";

export const useProductMutations = () => {
    const queryClient = useQueryClient();

    const createProductMutation = useMutation({
        mutationFn: (data: ProductFormData) => productApi.createProduct(data),
        onSuccess: (newProduct) => {
            const category = newProduct.data;

            queryClient.setQueryData<IPagedResponse<ProductFormData[]>>(
                ["pruducts"],
                (oldData) => {
                    if (oldData) {
                        return {
                            content: [...oldData.content, category],
                            page: oldData.page,
                        };
                    }

                    return {
                        content: [category],
                        page: { size: 1, number: 0, totalElements: 1, totalPages: 1 },
                    };
                }
            );
        },
        onError: (error) => {
            if (error instanceof AxiosError) {
                toast.error(error.response?.data?.error || "Erro na requisição Post.");
            }
        },
    });


    return {
        createProductMutation
    }
}