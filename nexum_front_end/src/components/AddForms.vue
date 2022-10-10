<template>
  <main>
    <section class="flex flex-row justify-between items-center">
      <h1 class="font-sans text-xl p-3">Dados da Pessoa</h1>
    </section>
    <hr />
    <form
      @submit.prevent="
        handleClick(
          $refs.nome.value,
          $refs.email.value,
          $refs.tipoPF.value,
          $refs.cpf.value,
          $refs.cnpj.value,
          $refs.cep.value,
          $refs.logradouro.value,
          $refs.num.value,
          $refs.cidade.value,
          $refs.bairro.value,
          $refs.estado.value
        )
      "
    >
      <section class="flex flex-row p-3">
        <label class="block basis-3/5 m-5">
          Nome Completo
          <input type="text" ref="nome" class="block border-2 h-10 w-full text-md" required v-model="pessoa.nome" />
        </label>
        <label class="block basis-2/5 m-5">
          E-mail
          <input type="text" ref="email" class="block border-2 h-10 w-full text-md" required v-model="pessoa.email" />
        </label>
      </section>
      <section class="flex flex-row p-3">
        <label class="block basis-1/5 m-5">
          Tipo de Pessoa
          <select class="block border-2 h-10 w-full" ref="tipoPF" v-model="value" v-on:change="mudarCampoDoc(value)" required>
            <option selected value="true">Pessoa Física</option>
            <option value="false">Pessoa Jurídica</option>
          </select>
        </label>
        <label id="CPF" class="block basis-1/5 m-5">
          CPF
          <input
            type="text"
            id="inputCPF"
            ref="cpf"
            class="block border-2 h-10 w-full text-md"
            v-mask="'###.###.###-##'"
            v-model="pessoa.cpf"
          />
        </label>
        <label id="CNPJ" class="hidden basis-1/5 m-5">
          CNPJ
          <input type="text" id="inputCNPJ" ref="cnpj" class="block border-2 h-10 w-full text-md" v-mask="'##.###.###/####-##'" v-model="pessoa.cnpj" />
        </label>
      </section>
      <section>
        <section class="flex flex-row p-3">
          <label class="block basis-1/5 m-5">
            CEP
            <input
              type="text"
              id="cep"
              ref="cep"
              class="block border-2 h-10 w-full text-md"
              v-mask="'#####-###'"
              required
              v-model="pessoa.cep"
              v-on:change="handleClickCEP(pessoa.cep)"
              v-on:focus="handleClickCEP(pessoa.cep)"
            />
          </label>
          <label class="block basis-3/5 m-5">
            Logradouro
            <input type="text" ref="logradouro" class="block border-2 h-10 w-full text-md" required onkeypress="return false;" v-model="endereco.logradouro" />
          </label>
          <label class="block basis-1/8 m-5">
            Número
            <input type="text" ref="num" class="block border-2 h-10 w-full text-md" required v-model="pessoa.numero" />
          </label>
        </section>
        <section class="flex flex-row p-3">
          <label class="block basis-2/5 m-5">
            Cidade
            <input type="text" ref="cidade" class="block border-2 h-10 w-full text-md" required onkeypress="return false;" v-model="endereco.localidade" />
          </label>
          <label class="block basis-2/5 m-5">
            Bairro
            <input type="text" ref="bairro" class="block border-2 h-10 w-full text-md" required onkeypress="return false;" v-model="endereco.bairro" />
          </label>
          <label class="block basis-1/5 m-5">
            Estado
            <input type="text" ref="estado" class="block border-2 h-10 w-full text-md" required onkeypress="return false;" v-model="endereco.uf" />
          </label>
        </section>
      </section>

      <section class="flex flex-row p-3">
        <router-link to="/">
          <button type="button" class="font-sans text-md m-3 p-2 bg-zinc-400 rounded-md text-white">Cancelar</button>
        </router-link>
        <button class="font-sans text-md m-3 p-2 bg-sky-500 rounded-md text-white" @click="validarCPF(pessoa.cpf) && validarCNPJ(pessoa.cnpj)">Gravar</button>
      </section>
    </form>
  </main>
</template>

<script setup>
import { computed, onMounted } from "@vue/runtime-core";
import { useRoute } from "vue-router";
import { mask } from "vue-the-mask";
import { usePessoasStore } from "../stores/pessoas";
import { useEnderecoStore } from "../stores/endereco";
import router from "../router";

const store = usePessoasStore();
const pessoa = computed(() => store.pessoa);
const route = useRoute();

const storeCEP = useEnderecoStore();
const endereco = computed(() => storeCEP.endereco);

async function handleClick(nome, email, tipoPF, cpf, cnpj, cep, logradouro, num, cidade, bairro, estado) {
  if (pessoa.value.id) {
    await store.atualizarPessoa(route.params.id, nome, email, tipoPF, cpf, cnpj, cep, logradouro, num, cidade, bairro, estado);
  } else {
    await store.criarPessoa(nome, email, tipoPF, cpf, cnpj, cep, logradouro, num, cidade, bairro, estado);
  }

  router.go(-1);
}

onMounted(async () => {
  if (route.params.id) {
    await store.consultarUm(route.params.id);
    document.getElementById("cep").focus();
  }

  if (localStorage.getItem("reloaded")) {
    localStorage.removeItem("reloaded");
  } else {
    localStorage.setItem("reloaded", "1");
    location.reload();
  }
});

//Máscara
directives: {
  mask;
}

//Campo de documento
var value = true;
function mudarCampoDoc(value) {
  if (value == "true") {
    document.getElementById("CPF").style.display = "block";
    document.getElementById("CNPJ").style.display = "none";
    document.getElementById("inputCNPJ").value = "";
  } else {
    document.getElementById("CPF").style.display = "none";
    document.getElementById("CNPJ").style.display = "block";
    document.getElementById("inputCPF").value = "";
  }
}

//VIACEP
function handleClickCEP(cep) {
  if (cep) {
    cep.replace("-", "");
    storeCEP.consultarUm(cep);
  }
}

//Validações
async function validarCPF(inputCPF) {
  var soma = 0;
  var resto;
  if (inputCPF) {
    var CPF = inputCPF.replace(/[^\d]+/g, "");

    if (CPF == "00000000000") {
      let validar = document.getElementById("inputCPF");
      await validar.setCustomValidity("CPF inválido.");
      await validar.reportValidity();
      return;
    }

    for (let i = 1; i <= 9; i++) {
      soma = soma + parseInt(CPF.substring(i - 1, i)) * (11 - i);
    }
    resto = (soma * 10) % 11;

    if (resto == 10 || resto == 11) {
      resto = 0;
    }
    if (resto != parseInt(CPF.substring(9, 10))) {
      let validar = document.getElementById("inputCPF");
      await validar.setCustomValidity("CPF inválido.");
      await validar.reportValidity();
      return;
    }

    soma = 0;

    for (let i = 1; i <= 10; i++) {
      soma = soma + parseInt(CPF.substring(i - 1, i)) * (12 - i);
    }
    resto = (soma * 10) % 11;

    if (resto == 10 || resto == 11) {
      resto = 0;
    }
    if (resto != parseInt(CPF.substring(10, 11))) {
      let validar = document.getElementById("inputCPF");
      await validar.setCustomValidity("CPF inválido.");
      await validar.reportValidity();
      return;
    }

    return document.getElementById("inputCPF").setCustomValidity("");
  }
}

async function validarCNPJ(inputCNPJ) {
  inputCNPJ = inputCNPJ.replace(/[^\d]+/g, "");

  if (
    inputCNPJ == "00000000000000" ||
    inputCNPJ == "11111111111111" ||
    inputCNPJ == "22222222222222" ||
    inputCNPJ == "33333333333333" ||
    inputCNPJ == "44444444444444" ||
    inputCNPJ == "55555555555555" ||
    inputCNPJ == "66666666666666" ||
    inputCNPJ == "77777777777777" ||
    inputCNPJ == "88888888888888" ||
    inputCNPJ == "99999999999999"
  ) {
    let validar = document.getElementById("inputCNPJ");
    await validar.setCustomValidity("CNPJ inválido.");
    await validar.reportValidity();
    return;
  }


  let tamanho = inputCNPJ.length - 2;
  let numeros = inputCNPJ.substring(0, tamanho);
  let digitos = inputCNPJ.substring(tamanho);
  soma = 0;
  pos = tamanho - 7;

  for (i = tamanho; i >= 1; i--) {
    soma += numeros.charAt(tamanho - i) * pos--;
    if (pos < 2) pos = 9;
  }
  resultado = soma % 11 < 2 ? 0 : 11 - (soma % 11);

  if (resultado != digitos.charAt(0)) {
    let validar = document.getElementById("inputCNPJ");
    await validar.setCustomValidity("CNPJ inválido.");
    await validar.reportValidity();
    return
  }

  tamanho = tamanho + 1;
  numeros = inputCNPJ.substring(0, tamanho);
  soma = 0;
  pos = tamanho - 7;

  for (i = tamanho; i >= 1; i--) {
    soma += numeros.charAt(tamanho - i) * pos--;
    if (pos < 2) pos = 9;
  }

  resultado = soma % 11 < 2 ? 0 : 11 - (soma % 11);
  
  if (resultado != digitos.charAt(1)) {
    let validar = document.getElementById("inputCNPJ");
    await validar.setCustomValidity("CNPJ inválido.");
    await validar.reportValidity();
  };

  return document.getElementById("inputCNPJ").setCustomValidity("");
}
</script>
