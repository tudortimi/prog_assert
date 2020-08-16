// Copyright 2015-2020 Tudor Timisescu (verificationgentleman.com)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


`ifndef __PROG_ASSERT
`define __PROG_ASSERT


`ifdef NDEBUG
  `define prog_assert(expr, msg = "") \
    begin \
    end
`else
  `define prog_assert(expr, msg = "") \
    begin \
      if (!(expr)) begin \
        automatic string error_msg = $sformatf("Assertion '%s' failed.", `"expr`"); \
        if (msg != "") \
          error_msg = { error_msg, "\n", msg }; \
`ifdef INCA \
        $stacktrace; \
`endif \
        $fatal(0, error_msg); \
      end \
    end
`endif


`ifdef NDEBUG
  `define prog_verify(expr, msg = "") \
    begin \
      void'(expr); \
    end
`else
  `define prog_verify(expr, msg = "") \
    `prog_assert(expr, msg)
`endif


`define prog_cast(src, dst, msg = "") \
  `prog_verify($cast(src, dst), msg)


`define prog_unreachable \
  `prog_assert(0, "Execution should never reach this point.")


`endif
