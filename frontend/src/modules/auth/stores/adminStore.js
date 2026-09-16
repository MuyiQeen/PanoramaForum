import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const adminStore = defineStore('adminStore', ()=>{
    const access_token = ref('')
    const user = ref('')
    const isAuthenticated = ref('')
    const loading = ref(false)
    const islogged = computed(()=>!access_token.value && !user.value)

    /**
     * admin login
     * @param {string} email 
     * @param {string} password 
     * @returns 
     */
    const login = async(email, password)=>{
        if(!email.trim() || !password.trim())return ''

        await fetch('')
    }

})